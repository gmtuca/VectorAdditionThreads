filename="$1"
name="$2"

gnuplot <<- EOF
    set xlabel "Number of Threads"
    set ylabel "Performance (runs/sec)"
    set term png
    set output "${filename}.png"
    plot "${filename}" using 1:2 with lines title "Performance with vector size ${name}"
EOF

eog "${filename}.png"
