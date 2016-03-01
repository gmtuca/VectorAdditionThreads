#!/bin/sh
#
# request Bourne shell as shell for job
#$ -S /bin/sh
#
# use current working directory
#$ -cwd
#
# join the output and error output in one file
#$ -j y
#
# use the par environment for parallel jobs
#$ -pe par 32
#
# set up affinity mask (defines cores on which to run threads)
# default mask

filename="datapoints"

#clear file
> "${filename}.txt"

length=1000000

for p in 1 2 3 4 5 6 7 8 12 16 20 24 28 32
do
    performance=$(numactl --physcpubind=0,1,2,4,5,6,8,9,10,12,13,14,16,17,18,20,21,22,24,25,26,28,29,31,32,33,34,36,37,38,40,41,42,44,45,46 java Demo $p $length)
    echo "$p $performance"
    echo "$p $performance" >> "${filename}.txt"
done

gnuplot <<- EOF
    set xlabel "Number of Threads"
    set ylabel "Performance (runs/sec)"
    set term png
    set output "${filename}.png"
    plot "${filename}.txt" using 1:2 with lines title "Performance of Vector Addition with Threads"
EOF

eog "${filename}.png"