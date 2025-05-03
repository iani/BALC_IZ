//:土  3  5 2025 17:15 alternative tests
// with Users.enabl using BALC class.

Pbindef(\snare2, \instrument, \default).play;
//:
Pbindef(\snare2,
	\instrument, \bf,
	\buf, \drum1.babuf,
	\dur, BAMetro.industone(0.25, 0.5, 1, 0.25, repeat:inf)*0.5,
	\amp, 1,
	\shape,Prand([0.01, 0.05, 0.03],inf),
	\pan, Pseq((-10..10).palindrome.stutter(3).normalize(-1.0, 1.0), inf)
).play;
