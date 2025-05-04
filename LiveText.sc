//VA 240909 Map Text to num
LiveText{



 	classvar <>server;
//	classvar <> text;
	var name;



	// Constructor

	*new{
		arg s, n, textPath;
		var obj, sample;


		server = Server.local;



		obj = super.new;

		obj.init(n);

		//obj.initCollectChars;

		^obj;
	}

	init{ arg n;


//----------------------------------------------------------------------



		n = name;
	}


//collect char from text

*text{|text, rep=0|
var loopy;

	fork{


0.1.wait;

~texts = [

//loopy = Routine({ text.do({ arg i; i.yield }) }).loop;//comment out this line to define the size of the array
//if(rep==0){rep.text.size};
~ints = Array.new.addAll(text) collect: { |char|
//~ints = Array.new.addAll(loopy.nextN(rep)) collect: { |char|////comment out this line to define the size of the array


IdentityDictionary[
$a->1,
$b->2,
$c->3,
$d->4,
$e->5,
$f->6,
$g->7,
$h->8,
$i->9,
$j->10,
$k->11,
$l->12,
$m->13,
$n->14,
$o->15,
$p->16,
$q->17,
$r->18,
$s->19,
$t->20,
$u->21,
$v->22,
$w->23,
$x->24,
$y->25,
$z->26,

$A->1,
$B->2,
$C->3,
$D->4,
$E->5,
$F->6,
$G->7,
$H->8,
$I->9,
$J->10,
$K->11,
$L->12,
$M->13,
$N->14,
$O->15,
$P->16,
$Q->17,
$R->18,
$S->19,
$T->20,
$U->21,
$V->22,
$W->23,
$X->24,
$Y->25,
$Z->26,

$,->Rest(1),
$.->Rest(2),
$?->Rest(2),
$!->Rest(2),

$ ->0.0,
$;->Rest(1.5),
$"->0.0,
$'->0.0
].at(char);
	/*
$,->\rest,
$.->\rest,
$?->\rest,
$!->\rest,
$'->\rest,
$ ->\rest
].at(char);
		*/
};
,
~floats = Array.new.addAll(text) collect: { |char|

IdentityDictionary[
$a->0.1,
$b->0.2,
$c->0.3,
$d->0.4,
$e->0.5,
$f->0.6,
$g->0.7,
$h->0.8,
$i->0.9,
$j->0.10,
$k->0.21,
$l->0.32,
$m->0.43,
$n->0.54,
$o->0.65,
$p->0.76,
$q->0.87,
$r->0.98,
$s->0.19,
$t->0.220,
$u->0.321,
$v->0.422,
$w->0.523,
$x->0.624,
$y->0.725,
$z->0.826,

$A->0.1,
$B->0.2,
$C->0.3,
$D->0.4,
$E->0.5,
$F->0.6,
$G->0.7,
$H->0.8,
$I->0.9,
$J->0.10,
$K->0.21,
$L->0.32,
$M->0.43,
$N->0.54,
$O->0.65,
$P->0.76,
$Q->0.87,
$R->0.98,
$S->0.19,
$T->0.220,
$U->0.321,
$V->0.422,
$W->0.523,
$X->0.624,
$Y->0.725,
$Z->0.826,

$,->Rest(1),
$.->Rest(2),
$?->Rest(2),
$!->Rest(2),
	//$'->Rest(1),
$ ->0.0,
$;->Rest(1.5),
$"->0.0,
$'->0.0
	/*
$,->0.0,
$.->0.0,
$?->0.0,
$!->0.0,
$'->0.0,
$ ->0.0,
$;->0.0,
$"->0.0,
$'->0.0
	*/
].at(char);
};
];

0.1.wait;





};


"Global varaible '~ints', '~floats':
//Use with patterns.. \\freq, Pseq(~ints).play;".postln;

}


}
