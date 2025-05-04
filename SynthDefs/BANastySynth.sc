BANastySynth {
	classvar <>wackiness=200;
	
	*ar {arg wackymult=1, mod1=100, mod2=0.5, amp=1; 
		
		^CombN.ar(
			(SinOsc.ar(
				LFNoise0.ar(9, wackymult*wackiness, mod1), 7, mod2)%0.3)*BrownNoise.ar(amp)
		,0.3,0.3, 5)
	}

}