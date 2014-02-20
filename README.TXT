Mostafa Sadraii
Main Class: Jraw.java

I spent a lot of time making comments in the code using the javadoc standard. I commented almost all of the shape classes and many of the other classes. If you want to see those comments, you can browse the code or use javadoc to create a documentation directory for the project.

Things that don't work well:
- For some reason, even though I've looked over the code a few times, GroupShape doesn't seem to group all of the shapes that are selected. It picks a few shapes and only groups those. I don't know why this happens.
- When you load a file that has a GroupShape in it, the group is created and positioned right, but the shapes that were in it are not added to the group. So dragging the GroupShape will not drag its individual members.
- There are some redraw inconsistencies that I haven't had time to work through (mainly dealing with the small square handles for each shape)
- I wrote the entire project in Mac OS X. There are some inconsistencies between the Mac and Win/Linux versions of Java 2D's screen rendering. For example, if you run this program on Mac OS X, creating polygons have a very odd behavior on the screen as you are adding vertices (by clicking), but on the PC it looks normal. The reason I mention this is because there may be some things that will have done in order to make it work on my development platform, but those things may not be the right solution for the PC platform (or not, I haven't tested everything on both platforms).

Anyway, I enjoyed writing the project. I hope you have a good summer!

BTW, the program should at least work with the following commands:
>javac Jraw.java
>java Jraw