machine=sd302@l040101-ws06.ua.pt

echo "Executing program at the Plane node."
sshpass -f password ssh $machine 'cd test/AirLift/ ; java -cp .:./genclass.jar Server.PlaneMain'
