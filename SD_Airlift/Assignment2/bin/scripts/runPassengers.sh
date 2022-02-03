
#!/bin/bash
cd ..
allpids=()
for i in $(seq 0 20); do
	java  Client.PassengerMain $i &
done
echo "Time to die"
sleep 5
kill 0
