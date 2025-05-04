// =====================================================================
// SuperCollider Workspace - VA2016
// BASynths version I
// =====================================================================
 
BASynthGens{

classvar <>server;
	//	classvar <>metronomos;
	var name;



	// Constructor

	*new{
		arg n;
		var obj;


		server = Server.local;
		obj = super.new;

		obj.init(n);



		^obj;
	}

	init{ arg n;


	BAChaosCL();
	BAFmSynth();
	BANastyPad();
	BASpacePadA();
	BASamplerLiveAn();
	BASynthGrainAn();
	BASynthPad();
	BASynthSines();
	BASoundA();
	BASoundC();
	BABrownFlute();

		"BAChaosCL,BAFmSynth,BANastyPad,BASpacePadA,BASynthGrainAn,BASamplerLiveAn, BASoundA,BASynthPad,BASynthSines".postln;
	}
}