// =====================================================================
// SuperCollider Workspace - VA2016
// BASynths version I
// =====================================================================
BAMetro {

	//Disyllables
	*iambic {|metro = 1, repeat=1|
		//Pdef.all.clear;
		//var hexam = "uu-".postln;

		var nmetro = Pseq(["u-"]).repeat(metro).asStream.nextN(metro).strFlat;


		var listIamb = Array.new.addAll(nmetro) collect: { |char|
			IdentityDictionary[
				$-->1,
				$u->0.5
			].at(char);

		};
		^Pseq(listIamb.postln, repeat);
	}

	*spondee {|metro = 1, repeat=1|
		//Pdef.all.clear;
		//var hexam = "uu-".postln;

		var nmetro = Pseq(["--"]).repeat(metro).asStream.nextN(metro).strFlat;


		var listSpondee = Array.new.addAll(nmetro) collect: { |char|
			IdentityDictionary[
				$-->1,
				$u->0.5
			].at(char);

		};
		^Pseq(listSpondee.postln, repeat);
	}
	*trochee {|metro = 1, repeat=1|

		var nmetro = Pseq(["-u"]).repeat(metro).asStream.nextN(metro).strFlat;


		var listTrochee = Array.new.addAll(nmetro) collect: { |char|
			IdentityDictionary[
				$-->1,
				$u->0.5
			].at(char);

		};
		^Pseq(listTrochee.postln, repeat);
	}

	*pyrrhic {|metro = 1, repeat=1|

		var nmetro = Pseq(["uu"]).repeat(metro).asStream.nextN(metro).strFlat;


		var listPyrrhic = Array.new.addAll(nmetro) collect: { |char|
			IdentityDictionary[
				$-->1,
				$u->0.5
			].at(char);

		};
		^Pseq(listPyrrhic.postln, repeat);
	}


	//Trisyllables
	*anapest {|metro = 1, repeat=1|

		var nmetro = Pseq(["uu-"]).repeat(metro).asStream.nextN(metro).strFlat;


		var listAnapest = Array.new.addAll(nmetro) collect: { |char|
			IdentityDictionary[
				$-->1,
				$u->0.5
			].at(char);

		};

		//listAnapest=[listAnapest[[0,1,2]],listAnapest[[0,1,2]], listAnapest[[0,1,2]]].strFlat;
		^Pseq(listAnapest.postln, repeat);

	}

	*dactyl {|metro = 1, repeat=1|
		//Pdef.all.clear;
		//var hexam = "uu-".postln;

		var nmetro = Pseq(["-uu"]).repeat(metro).asStream.nextN(metro).strFlat;


		var listDactyl = Array.new.addAll(nmetro) collect: { |char|
			IdentityDictionary[
				$-->1,
				$u->0.5
			].at(char);

		};

		//listAnapest=[listAnapest[[0,1,2]],listAnapest[[0,1,2]], listAnapest[[0,1,2]]].strFlat;
		^Pseq(listDactyl.postln, repeat);

	}


	*amphibrach {|metro = 1, repeat=1|
		//Pdef.all.clear;
		//var hexam = "uu-".postln;

		var nmetro = Pseq(["u-u"]).repeat(metro).asStream.nextN(metro).strFlat;


		var listAmphibrach = Array.new.addAll(nmetro) collect: { |char|
			IdentityDictionary[
				$-->1,
				$u->0.5
			].at(char);

		};
		^Pseq(listAmphibrach.postln, repeat);
	}

	*tribrach {|metro = 1, repeat=1|

		var nmetro = Pseq(["uuu"]).repeat(metro).asStream.nextN(metro).strFlat;


		var listTribrach = Array.new.addAll(nmetro) collect: { |char|
			IdentityDictionary[
				$-->1,
				$u->0.5
			].at(char);

		};
		^Pseq(listTribrach.postln, repeat);
	}

	*bacchius {|metro = 1, repeat=1|

		var nmetro = Pseq(["u--"]).repeat(metro).asStream.nextN(metro).strFlat;


		var listBacchius = Array.new.addAll(nmetro) collect: { |char|
			IdentityDictionary[
				$-->1,
				$u->0.5
			].at(char);

		};
		^Pseq(listBacchius.postln, repeat);
	}

	*antibacchius {|metro = 1, repeat=1|

		var nmetro = Pseq(["--u"]).repeat(metro).asStream.nextN(metro).strFlat;


		var listAntibacchius = Array.new.addAll(nmetro) collect: { |char|
			IdentityDictionary[
				$-->1,
				$u->0.5
			].at(char);

		};
		^Pseq(listAntibacchius.postln, repeat);
	}

	*cretic {|metro = 1, repeat=1|

		var nmetro = Pseq(["-u-"]).repeat(metro).asStream.nextN(metro).strFlat;


		var listCretic = Array.new.addAll(nmetro) collect: { |char|
			IdentityDictionary[
				$-->1,
				$u->0.5
			].at(char);

		};
		^Pseq(listCretic.postln, repeat);
	}


	*molossus {|metro = 1, repeat=1|

		var nmetro = Pseq(["---"]).repeat(metro).asStream.nextN(metro).strFlat;


		var listMolossus = Array.new.addAll(nmetro) collect: { |char|
			IdentityDictionary[
				$-->1,
				$u->0.5
			].at(char);

		};
		^Pseq(listMolossus.postln, repeat);
	}



	//Metric Western

	*duple {|note1=1,note2=0.5, repeat=1|
		//var nmetro = Pseq([note1, note2]).repeat(metro).asStream.nextN(metro);
		var nmetro = Pseq(["-u"]).repeat(2).asStream.nextN(2).strFlat;


		var listDuple = Array.new.addAll(nmetro) collect: { |char|
			IdentityDictionary[
				$-->note1,
				$u->note2
			].at(char);

		};
		^Pseq(listDuple.postln, repeat);
	}

	*triple{|note1=1,note2=0.5, note3 = 0.125, repeat=1|
		//var nmetro = Pseq([note1, note2]).repeat(metro).asStream.nextN(metro);
		var nmetro = Pseq(["-u|"]).repeat(3).asStream.nextN(3).strFlat;


		var listTriple = Array.new.addAll(nmetro) collect: { |char|
			IdentityDictionary[
				$-->note1,
				$u->note2,
				$|->note3
			].at(char);

		};
		^Pseq(listTriple.postln, repeat);
	}

	//backbeat

	*backbeat {|note1=0.5, note2=1, rest, repeat=1|
		//var nmetro = Pseq([note1, note2]).repeat(metro).asStream.nextN(metro);
		var nmetro = Pseq(["|uu|-"]).repeat(2).asStream.nextN(2).strFlat;


		var listBackBeat = Array.new.addAll(nmetro) collect: { |char|
			IdentityDictionary[
				$-->note1,
				$u->note2,
				$|->Rest(1)
			].at(char);

		};
		^Pseq(listBackBeat.postln, repeat);
	}

	*triola {|metro = 1, repeat=1|

		var nmetro = Pseq(["uuu|"]).repeat(metro).asStream.nextN(metro).strFlat;


		var listTriola = Array.new.addAll(nmetro) collect: { |char|
			IdentityDictionary[
				$-->1,
				$u->0.25,
				$|->Rest(0.25)
			].at(char);

		};
		^Pseq(listTriola.postln, repeat);
	}


	//industrial

	*industone {|note1=1,note2=1,note3=0.5,note4=0.5, repeat=1|

		var nmetro = Pseq(["-^u%"]).repeat(4).asStream.nextN(1).strFlat;


		var listIndustone = Array.new.addAll(nmetro) collect: { |char|
			IdentityDictionary[
				$-->note1,
				$^->note2,
				$u->note3,
				$%->note4

			].at(char);

		};
		^Pseq(listIndustone.postln, repeat);
	}

}