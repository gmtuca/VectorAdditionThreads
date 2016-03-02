filename="$1"
name="$2"

gnuplot <<- EOF
    set xlabel "Number of Threads"
    set ylabel "Performance (runs/sec)"
    set term png
    set style line 1 lt 1 lw 1 pt 2 linecolor rgb "blue"
    set style line 2 lt 1 lw 1 pt 2 linecolor rgb "red"
    set output "${filename}_lines.png"
    plot "${name}" using 1:2 with points title "", "" using 1:2 smooth csplines with lines linestyle 1 title ""
    set output "${filename}_errorbars.png"
    plot "${name}" using 1:2:3 with errorbars linestyle 2 title ""
EOF

eog "${filename}_errorbars.png"
eog "${filename}_lines.png"



#plot "file.txt" using 1:2:3 with yerrorbars
#You may instead want candlesticks. These are generally a box with error bars extending out of the top and bottom, but setting the mins and maxes the same should give you boxes of the required size:

#plot "file.txt" using 1:($2-$3):($2-$3):($2+$3):($2+$3) with candlesticks