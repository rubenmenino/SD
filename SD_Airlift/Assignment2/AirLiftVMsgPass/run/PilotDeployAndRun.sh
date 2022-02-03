machine=sd302@l040101-ws01.ua.pt

echo "Executing program at the pilot node."
sshpass -f password ssh $machine 'cd test/AirLift/ ; java -cp .:./genclass.jar Client.PilotMain'
