package model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Vector;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Game extends BasicGame{


	public static Game g;
	public static int currentPlayer=1;
	public static int resX;
	public static int resY;
	public AppGameContainer app;

	public Plateau plateau;

	public Color bgcolor = Color.black;
	
	
	



	/////////////////////////////
	/// NETWORK & MULTIPLAYING///
	/////////////////////////////

	boolean multiplayer = true;
	boolean host;
	
	// Host and client
	String iphost = "25.80.20.162";
	String ipclient = "25.80.123.241";
	InetAddress iahost;
	InetAddress iaclient;
	// port
	public int port = 2302;
	// depots for senders
	DatagramSocket server;
	DatagramSocket client;
	// depots for receivers
	public Vector<byte[]> receivedMessage = new Vector<byte[]>();



	public Game(int resolutionX, int resolutionY) {
		super("Vicier");
		Game.g = this;
		GameSound.init();
		GameSound.verdi.loop();
		resX = resolutionX;
		resY = resolutionY;
		Data.ratioSpace = Game.resX/Data.sizeXPlateau;
		this.plateau = new Plateau();
		try {
			iahost = InetAddress.getByName(iphost);
			iaclient = InetAddress.getByName(ipclient);
			this.host = InetAddress.getLocalHost().equals(iahost);
			boolean flag = false;
			Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
			while(!flag && e.hasMoreElements())
			{
			    NetworkInterface n = (NetworkInterface) e.nextElement();
			    Enumeration<InetAddress> ee = n.getInetAddresses();
			    while (!flag && ee.hasMoreElements())
			    {
			        InetAddress i = (InetAddress) ee.nextElement();
			        this.host = i.equals(iahost);
			        flag = this.host;
			    }
			}

			if(this.host){
				System.out.println("vaneau");
				Game.currentPlayer = 1;
			}else{
				System.out.println("vaneau2");
				Game.currentPlayer = 2;
			}
		} catch (UnknownHostException | SocketException e) {}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.setColor(bgcolor);
		g.fillRect(0, 0, resX, resY);
		this.plateau.draw(g);
		
		
	}

	@Override
	public void init(GameContainer gc) throws SlickException {

		try {
			client = new DatagramSocket();
			server = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}


	}

	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {
		InputModel im = new InputModel(gc.getInput());
		if(!multiplayer || host){
			Vector<InputModel> ims = new Vector<InputModel>();
			ims.add(im);
			this.handleMultiReceiver();
			if(multiplayer && this.receivedMessage.size()>0){
				ims.add(Game.getInputModelFromString(this.receivedMessage.remove(0)));
			} else {
				ims.add(new InputModel());
			}
			this.plateau.update(ims);
			if(this.plateau.winner!=0){
				if(im.isPressedRightClick){
					this.plateau = new Plateau();
				}
			}
			// on envoie le plateau
			this.send(serialize(this.plateau));
		} else {
			// on envoie l'input
			this.send(serialize(im));
			// on recoit le plateau
			this.handleMultiReceiver();
			if(this.receivedMessage.size()>0){
				this.updatePlateauFromString(this.receivedMessage.remove(0));
			}
		}
		
		// Play plateau sounds
		for(Integer i : plateau.soundsToPlay){
			Sound s = GameSound.getSoundById(i);
			if( s!=null){
				s.play();
			}
		}
		plateau.soundsToPlay.clear();
		
		

	}

	public static float getPointToDraw(float f){
		return f*Game.resX/Data.sizeXPlateau;
	}

	public void updatePlateauFromString(byte[] serializedObject){
		// deserialize the object
		try {
			ByteArrayInputStream bi = new ByteArrayInputStream(serializedObject);
			ObjectInputStream si = new ObjectInputStream(bi);
			this.plateau = (Plateau) si.readObject();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static InputModel getInputModelFromString(byte[] serializedObject){
		// deserialize the object
		try {
			ByteArrayInputStream bi = new ByteArrayInputStream(serializedObject);
			ObjectInputStream si = new ObjectInputStream(bi);
			return (InputModel) si.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return new InputModel();
		}

	}

	
	public static byte[] serialize(Object o){
		byte[] serializedObject = new byte[0];
		// serialize the object
		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream so = new ObjectOutputStream(bo);
			so.writeObject(o);
			so.flush();
			serializedObject = bo.toByteArray();
		} catch (Exception e) {
			System.out.println(e);
		}
		return serializedObject;		
	}

	private void handleMultiReceiver() throws SlickException {
		this.receivedMessage.clear();
		while(true){
			byte[] message = new byte[10000];
			DatagramPacket packet = new DatagramPacket(message, message.length);
			try {
				server.setBroadcast(false);
				server.setSoTimeout(1);
				server.receive(packet);
				System.out.println("vaneau");
				this.receivedMessage.addElement(packet.getData());

			} catch (SocketTimeoutException e) {
				break;
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public void send(byte[] mb) {
		//si on est sur le point de commencer à jouer, on n'envoie plus de requête de ping
		InetAddress address = iahost;
		if(host){
			address = iaclient;
		}
		DatagramPacket packet = new DatagramPacket(mb, mb.length, address, this.port);
		packet.setData(mb);
		try {
			client.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}