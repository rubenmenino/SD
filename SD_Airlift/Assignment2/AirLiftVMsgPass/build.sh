
rm -f  dirAirLift.zip
zip -rq dirAirLift.zip Client Server Common ./genclass.jar


for i in $(seq 9)
do
  	machine=sd302@l040101-ws0$i.ua.pt
	echo "Transfering data to the node $i."
	sshpass -f password ssh $machine 'mkdir -p test/AirLift'
	sshpass -f password ssh $machine 'rm -rf test/AirLift/*'
	sshpass -f password scp dirAirLift.zip $machine:test/AirLift
	echo "Decompressing data sent to the node $i."
	sshpass -f password ssh $machine 'cd test/AirLift ; unzip -uq dirAirLift.zip'
	echo "compiling..."
	sshpass -f password ssh $machine 'cd test/AirLift ;find . -name "*.java" -type f > sources.txt ; javac -cp "./genclass.jar" @sources.txt'
done

machine=sd302@l040101-ws10.ua.pt
	echo "Transfering data to the node 10."
	sshpass -f password ssh $machine 'mkdir -p test/AirLift'
	sshpass -f password ssh $machine 'rm -rf test/AirLift/*'
	sshpass -f password scp dirAirLift.zip $machine:test/AirLift
	echo "Decompressing data sent to the node 10."
	sshpass -f password ssh $machine 'cd test/AirLift ; unzip -uq dirAirLift.zip'
	echo "compiling..."
	sshpass -f password ssh $machine 'cd test/AirLift ;find . -name "*.java" -type f > sources.txt ; javac -cp "./genclass.jar" @sources.txt'

rm -f  dirAirLift.zip

