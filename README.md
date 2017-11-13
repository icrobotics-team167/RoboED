# RoboED
> You should do a Mario analogy!
&mdash; <cite>Frances</cite>

RoboED is a FRC robot control library that uses a pipeline pattern to model the flow of data. Sensors and controllers produce data which flows through a pipeline system and eventually gets consumed by actuators and whatever other data consumers might exist.

## Can you go more in-depth about the data model?
*TODO*

## How do I use this?
*TODO*

## Can I get some API documentation?
Most of the library code is commented, so your IDE should be able to provide documentation where needed. Real API docs will be coming soon(?).

## What's up with the name?
When I started writing this library, I was thinking something along the lines of "you know what would be really cool? Event-driven robots". Halfway through developing Robo-Event-Driven, however, I realized how bad of an idea it was and scrapped it. Later, when I came up with an actually reasonable model for robot control, I just built it on the skeleton of a project left from RoboED and kept the name.
