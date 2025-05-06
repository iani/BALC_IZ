
/*
~snare1 = Pbindef(\snare, \freq,[10,20], \dur, Pbindef(\snare2, \instrument, \default).play;
, \amp, 0.8, \shape,1, \buf, ~bufs[3].postln, \pan, 0.4).play;

~kick1 = Pbindef(\kick1, \freq,120, \dur, BAMetro.industone(0.25, 0.5, 1, 0.25, repeat:inf)*0.5, \amp, 0.7, \shape,[0.01, 0.2], \buf, ~bufs[0], \pan, 0.1).play;

~hihat1 = Pbindef(\hihat1, \freq,390, \dur, BAMetro.duple(0.25, 0.25, repeat:inf)*0.5, \amp, 0.6, \shape,Prand([0.01, 0.05, 0.03],inf),\buf, ~bufs[7], \pan, -0.5).play;
*/

//: ============= Initialize BALC and enable User explicitly ============
// Test these in different order to check compatibility
BALC.init;
User.enable;
BALC.globalizeBufs; // this writes the buferss by their name to the global parent dictionary
// it is useful for playing buffers with patterns
// Instead of \drum1.babuf you write ~drum1

//: ===== Try these with BALC inited and User enabled =====
\snare1 play: (instrument: \default);
\snare1.stop;
\snare1.play;
~snare1;
~snare1.play;
~snare1.start;
~snare1.stop;
//: ====== Test 1: Modify patterns with User enabled ======
//: ====== Try this with User enabled to check changing pattern while running ====
User.enable;
\snare1 play: (instrument: \default);
\snare1 play: (degree: [1, 3]);
\snare1.stop; // stops
\snare1.play; // restarts
\snare1 play: (degree: [1, 3].neg);
\snare1 play: (degree: [1, 3].neg.pseq);
\snare1 play: (dur: 0.1);
\snare1.clear; // stops and stores an empty EventStream
\snare1.play;  // plays the empty EventStream

~drum1;
//: Test 1a: Gradual modification of parameters in small numbers
// Checked this with BALC inited and User enabled:
BALC.init;
User.enable;
\snare1 play: (instrument: \bf, buf: ~drum1, amp: 1);
\snare1 play: (dur: 0.5);
\snare1 set: (dur: 0.2); // same as play but does not start if not playing
~myfunc = { 0.01 exprand: 1.0 };
\snare1 play: (dur: ~myfunc.pfunc, amp: ~myfunc.pfunc);
\snare1 set: (dur: 0.01);
\snare1 set: (amp: ~myfunc.pfunc * 0.3);
\snare1 set: (amp: ~myfunc.pfunc * [0.1, 0.3, 0.6].pbrown);
\snare1 set: (dur: 0.1, amp: 0.5);
\snare1 set: (buf: \drum2.babuf, amp: ~myfunc.pfunc * [0.1, 0.3, 0.6].pbrown);
\snare1 set: (buf: \drum1.babuf);
\snare1 set: (dur: 1, amp: 0.4);
\snare1 set: (shape: 1, amp: 2);
\snare1 set: (pan: 0.9);
//:
\snare1.play; // restart
// experiment with frequency
\snare1 play: (freq: 10);
\snare1 play: (freq: [10, 20]);
\snare1.stop;
//: Test 1b: Many parameter keys at once
\snare2 play: (freq: [30,40],
	instrument: \bf,
	dur: BAMetro.industone(0.25, 0.5, 1, 0.25, repeat:inf)*0.5,
	amp: 0.8, shape: 1, buf: \drum1.babuf, pan: 0.1
);
//:
\snare2 play: (freq: [10, 20] * 2);
//:
\snare2 play: (freq: ((1..50).normalize(0.95, 5).palindrome).pseq * [10, 20]);
//:
\snare2.stop;

\snare2 play: (buf: ~drum2, shape: 1);
\snare3 play: (buf: \drum1.babuf, shape: 1);
//:
\snareX play: (freq: [30,40],
	instrument: \bf,
	//	dur: BAMetro.industone(0.25, 0.5, 1, 0.25, repeat:inf)*0.5,
	amp: 0.8, shape: 1, buf: \drum1.babuf, pan: 0.1
);
//: shortcut for playbuf with bf synthdef:
// make sure you run these first:
// BALC.init;
// BALC.globalizeBufs;

\blah.playbuf play: (
	freq: [300,400],
	dur: BAMetro.industone(0.25, 0.5, 1, 0.25, repeat:inf)*0.5,
	amp: 0.8, shape: 1,
	buf: ~drum2,
	pan: 0.1
);
//: Debugging
\x.playBuf

//: ====== Test 2: Use buffers + synthdefs from BALC ======
BALC.init; // initialize to load buffers
// ====== Also enable User =====
User.enable;
//: Tests porting code from Pbindefs
//~kick1 = Pbindef(\kick1, \freq,120, \dur, BAMetro.industone(0.25, 0.5, 1, 0.25, repeat:inf)*0.5, \amp, 0.7, \shape,[0.01, 0.2], \buf, ~bufs[0], \pan, 0.1).play;
\kick1 play: (freq: 120, instrument: \bf, dur: BAMetro.industone(0.25, 0.5, 1, 0.25, repeat:inf)*0.5, amp: 0.7, shape: [0.01, 0.2], buf: \drum1.babuf, pan: 0.1);
//:an alternative way to initialize a pattern before playing it is to use set:
\kick2 set: (instrument: \bf, dur: BAMetro.industone(0.25, 0.5, 1, 0.25, repeat:inf)*0.5, \buf: \drum1.babuf);
// after that use play to add more parameters and start playing:
\kick2 play: (freq: 120, amp: 0.7, shape: [0.01, 0.2], pan: 0.1);



// ~snare1 =
// Pbindef(\snare, \freq,[10,20], \dur, Pbindef(\snare2, \instrument, \default).play;
// , \amp, 0.8, \shape,1, \buf, ~bufs[3].postln, \pan, 0.4).play;
\drum1.babuf.play; // test buffer
\snare1 play: (instrument: \bf, buf: \drum1.babuf, amp: 1);
\snare1 set: (dur: 0.1);
~myfunc = { 0.01 exprand: 1.0 };
\snare1 play: (dur: ~myfunc.pfunc, amp: ~myfunc.pfunc);
\snare1 set: (dur: 0.01);
\snare1 set: (amp: ~myfunc.pfunc * 0.3);
\snare1 set: (amp: ~myfunc.pfunc * 1.5);


//: ====== test 3: Test synchronization with M tempoclock ======
//: 3a without metronome: not synchronized
{
	\snare1 play: ();
	0.25.wait;
	\snare2 play: (degree: 2);
}.fork

//: 3b with metronome: synchronized
M.sync;
{
	\snare1 play: ();
	0.25.wait;
	\snare2 play: (degree: 2);
}.fork
//: 3c with metronome: unsynchronized explicitly
M.async;
{
	\snare1 play: ();
	0.25.wait;
	\snare2 play: (degree: 2);
}.fork
//:
//: ====== Test 4: Test sending buffers from BALC to global dict ======
BALC.init;
User.enable;
BALC.globalizeBufs;
\wonderbuf play: (instrument: \bf, buf: ~drum1, amp: 1);
\wonderbuf play: (buf: ~drum5);
\wonderbuf set: (buf: ~drum_Noisykick01);
\wonderbuf.stop;

//: ====== Test 5: Translate another Pbindef ======
// ~hihat1 = Pbindef(\hihat1, \freq,390, \dur, BAMetro.duple(0.25, 0.25, repeat:inf)*0.5, \amp, 0.6, \shape,Prand([0.01, 0.05, 0.03],inf),\buf, ~bufs[7], \pan, -0.5).play;
\hihat1 play: (\instrument: \bf, freq: 390, dur: BAMetro.duple(0.25, 0.25, repeat:inf)*0.5, amp: 0.6, shape: Prand([0.01, 0.05, 0.03],inf), buf: ~drum2, pan: -0.5);
//: ====== Test 5: Shortcuts for setting instrument and buffer ======
\wbuf.playBuf play: (dur: 0.2, amp: 1);
\wbuf2.playBuf(\drum2) play: (dur: 0.1, amp: 0.3);
\wbuf3.playBuf(\drum2).play;
