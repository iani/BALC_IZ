// 土  3  5 2025 14:35
// Create NDefs for BALC

BaNdefs {
	*init {
		Server.default doWhenBooted: {
			this.makeNdefs;
		};
	}

	*makeNdefs {
		"=== BaNdefs makes Ndefs ===".postln;
		Ndef(\kick1).put(0, {
			InFeedback.ar(\mbus1.babus, 2) }).fadeTime_(0.2).play;
		Ndef(\kick2).put(0, {
			InFeedback.ar(\mbus2.babus, 2) }).fadeTime_(0.2).play;
		Ndef(\kick3).put(0, {
			InFeedback.ar(\mbus3.babus, 2) }).fadeTime_(0.2).play;
		Ndef(\snare).put(0, {
			InFeedback.ar(\mbus4.babus, 2) }).fadeTime_(0.2).play;
		Ndef(\hihat1).put(0, {
			InFeedback.ar(\mbus5.babus, 2) }).fadeTime_(0.2).play;
		Ndef(\hihat2).put(0, {
			InFeedback.ar(\mbus6.babus, 2) }).fadeTime_(0.2).play;
		////////////////////////BASSES////////////////////////////////////

		Ndef(\bass1).put(0, {
			InFeedback.ar(\mbus9.babus, 2) }).fadeTime_(0.2).play;

		/////////////////PADS///////////////////

		Ndef(\p1).put(0, {
			InFeedback.ar(\mbus8.babus, 2) }).fadeTime_(0.2).play;
		Ndef(\pad1).put(0, {
			InFeedback.ar(\mbus10.babus, 2) }).fadeTime_(0.2).play;
		Ndef(\p4).put(0, {
			InFeedback.ar(\mbus11.babus, 2) }).fadeTime_(0.2).play;
		Ndef(\p5).put(0, {
			InFeedback.ar(\mbus12.babus, 2) }).fadeTime_(0.2).play;
		///////////////////////VOICES/////////////////////////
		Ndef(\voice1).put(0, {
			InFeedback.ar(\mbus13.babus, 2) }).fadeTime_(0.2).play;
		Ndef(\voice2).put(0, {
			InFeedback.ar(\mbus14.babus, 2) }).fadeTime_(0.2).play;
		Ndef(\p8).put(0, {
			InFeedback.ar(\mbus15.babus, 2) }).fadeTime_(0.2).play;
		Ndef(\p9).put(0, {
			InFeedback.ar(\mbus15.babus, 2) }).fadeTime_(0.2).play;
		Ndef(\p10).put(0, {
			InFeedback.ar(\mbus16.babus, 2) }).fadeTime_(0.2).play;
		"=== BaNdefs finished making Ndefs ===".postln;
	}
}