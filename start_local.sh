

some_command &
P1=$!
other_command &
P2=$!
wait $P1 $P2