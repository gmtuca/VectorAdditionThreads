#!/bin/sh

filename="datapoints"

#clear file
> "${filename}.txt"

runs=20
length=1000000
cores=14

for p in $(seq 1 $cores)
do
    total=0

    for t in $(seq 1 $runs)
    do
        total=$(($total + $(numactl --physcpubind=0,1,2,4,5,6,8,9,10,12,13,14 java Demo $p $length)))
    done

    avg=$(($total /  $runs))
    echo "$p $avg"
    echo "$p $avg" >> "${filename}.txt"
done

gnuplot <<- EOF
    set xlabel "Number of Threads"
    set ylabel "Duration (ns)"
    set term png
    set output "${filename}.png"
    plot "${filename}.txt" using 1:2 with lines title "Performance of Vector Addition with Threads"
EOF

eog "${filename}.png"