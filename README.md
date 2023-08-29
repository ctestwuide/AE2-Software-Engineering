# AE2-Software-Engineering
AE2 group project for software engineering

"Before the start of each term or semester, the class directors produce a
list of teaching requirements which we must try and fill. Our administrator
will then attempt to find suitable staff and organise training for them."

You should use an Agile approach to designing your software and Trello to communicate between
team members. You should design appropriate User Stories, but they do not have to be the same
as your stories from AE1. Please only include stories related to the limited part of the software
described above.

# The Assignment
DEADLINE: Friday 17th March 2023; 17:00

This is a follow up to Assessed Exercise 1 and, where possible, we will be using the same teams.
Please check your teams on Moodle as, due to changes in course numbers, some groups have
been reallocated between existing groups. If you have a new member please make them feel
welcome.

We will design software using UML Class and Sequence diagrams, and perform a small amount of
implementation work.

As a group you should design and implement the following part the Part Time Teachers Specification
from AE1:

Before the start of each term or semester, the class directors produce a
list of teaching requirements which we must try and fill. Our administrator
will then attempt to find suitable staff and organise training for them.

You should use an Agile approach to designing your software and Trello to communicate between
team members. You should design appropriate User Stories, but they do not have to be the same
as your stories from AE1. Please only include stories related to the limited part of the software
described above.

You should use sequence diagrams for each User Story to design your classes and methods, and
produce a Class Structure Diagram. Each class should be owned by one team member who is
responsible for implementing it (Note: members may be required to implement more than one
class depending on your design). I suggest plantuml (https://plantuml.com/) as a quick way to
draw UML diagrams, but you can use any approach you like, e.g. hand drawn, other tools etc.
Your stories will involve several different objects from different classes. Each class owner should
implement the part of the story related to their class. This is how stories are split into tasks.
You should use a simple implementation and develop a standalone app that does not require
a real database. All permanent information should be stored in a single file that is read in
when the program starts and then written to written to when the program finishes. Remember
your program will have an internal representation of this data, e.g. data-structures representing
classes, teachers etc. Don’t try and make the classes too elaborate as you don’t have much
time! These structures are empty when the program starts and then filled with data by reading
from the file. The updated version of the data is then preserved by writing to the file before the
program finishes. You should design this so that a better implementation, using a database, could
be provided in release 2 with minimum changes to the code base. You do not need to write a
database version for this exercise!

You will gain marks for using good design principles, e.g. ensuring low coupling and high cohesion,
and using design patterns, covered in future weeks, when appropriate. Marks will also be awarded
for working functionality.

Note: a well designed program where some things don’t quite work can score just as many marks
as a program that does everything but without good design principles.
