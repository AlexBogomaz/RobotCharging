# RobotCharging
Imitation charging robots based on the problem of the dining philosophers. 
But instead of philosophers we have 3 type of robot's. Greedy, gentlemane and random.
They all whant to charge to 100%.
6 robots have 6 details, but for charging they need 2 details.
Gentlemane robot gives his right or left detail if his neighbour have charge less then his.sleep 200ms
Greedy robot picks up details of his neighbour if he have charge less then 60%, then charge to 100%.sleep 500ms.
Random robot wait for two details charge 10% and sleep 100-300ms random.

