// Πρώτα τρέχεις το παρακάτω:

BADrumKit();

// Μετά δοκίμασε αυτά:

~snare1 = Pbindef(\snare, \freq,[10,20], \dur, BAMetro.industone(0.25, 0.5, 1, 0.25, repeat:inf)*0.5, \amp, 0.8, \shape,1, \buf, ~bufs[3].postln, \pan, 0.4).play;

~kick1 = Pbindef(\kick1, \freq,120, \dur, BAMetro.industone(0.25, 0.5, 1, 0.25, repeat:inf)*0.5, \amp, 0.7, \shape,[0.01, 0.2], \buf, ~bufs[0], \pan, 0.1).play;

~hihat1 = Pbindef(\hihat1, \freq,390, \dur, BAMetro.duple(0.25, 0.25, repeat:inf)*0.5, \amp, 0.6, \shape,Prand([0.01, 0.05, 0.03],inf),\buf, ~bufs[7], \pan, -0.5).play;

//: Debugging 金  2  5 2025 21:39

BADrumKit2();


//:

~snare1 = Pbindef(\snare, \instrument, \default).play;
~snare1.stop;
~snare1.play; //  ~snare1.start; // start not understood!
//:
~snare1 = Pbindef(\snare,
	\instrument, \default,
	\freq, [60, 70].midicps,
	\dur, BAMetro.industone(0.25, 0.5, 1, 0.25, repeat:inf)*0.5
).play;
