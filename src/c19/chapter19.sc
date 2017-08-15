import c19._

// 1
val bugsy = new Bug
bugsy.move(4).show().move(6).show().turn().move(5).show()

// 2
val show = Show
val then = Then
val around = Around

val fluentBugsy = new Bug with FluentBug
val fluentBugsy2 = new Bug with FluentBug

fluentBugsy.move(4).and(Show).and(Then).move(6).and(Show).turn(Around).move(5).and(Show)
fluentBugsy2 move 4 and show and then move 6 and show turn around move 5 and show
