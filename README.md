# montyhall-maven
a monty hall simulator
# config
edit in config.properties, default

lowestBox=1

highestBox=3

numberOfBoxes=highestBox - lowestBox + 1

iterations=10000

debugoutput=false
# run
mvn exec:java