machine=sd302@l040101-ws02.ua.pt

echo "Executing program at the hostess node."
sshpass -f password ssh $machine 'cd test/AirLift/ ; java -cp .:./genclass.jar Client.HostessMain'
