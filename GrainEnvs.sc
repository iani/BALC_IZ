
GrainEnvs {

	classvar server;
	classvar <winEnvHn, <hanning, <winEnvHm, <hamming, <winEnvS, <sine, <winEnvR, <rect, <winEnvC, <cheby, <winEnvW, <welch;

	*new{
arg n;
var obj;


server = Server.local;
obj = super.new;

obj.init(n);


^obj;
}

init{
		fork{

winEnvHn = Signal.hanningWindow(1024);
		Server.default.sync;
hanning = Buffer.sendCollection(server, winEnvHn, 1);
				Server.default.sync;

winEnvHm = Signal.hammingWindow(1024);
				Server.default.sync;

hamming = Buffer.sendCollection(server, winEnvHm, 1);
				Server.default.sync;

winEnvS = Signal.sineFill(1000, 1.0/[1, 2, 3, 4, 5, 6]);
				Server.default.sync;

sine = Buffer.sendCollection(server, winEnvS, 1);
				Server.default.sync;

winEnvR = Signal.rectWindow(1024);
				Server.default.sync;

rect = Buffer.sendCollection(server, winEnvR, 1);
				Server.default.sync;

winEnvC = Signal.chebyFill(1000, [0.3, -0.8, 1.1]);
				Server.default.sync;

cheby = Buffer.sendCollection(server, winEnvC, 1);
				Server.default.sync;

winEnvW = Signal.welchWindow(1024);
				Server.default.sync;

welch = Buffer.sendCollection(server, winEnvW, 1);
				Server.default.sync;
		};

	}

}
