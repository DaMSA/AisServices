package dk.dma.aisservices.core.services.ais;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import dk.dma.aisservices.core.services.ais.ShipTypeMapper.ShipTypeColor;
import dk.frv.ais.message.ShipTypeCargo;

public class OverviewResponse {
	
	private HashMap<Integer, ArrayList<String>> ships = new HashMap<Integer, ArrayList<String>>();
	private long currentTime;
	private long shipCount = 0;
	
	private static ShipTypeMapper shipTypeMapper = ShipTypeMapper.getInstance();
	
	public OverviewResponse() {
		currentTime = System.currentTimeMillis();
	}
	
	public HashMap<Integer, ArrayList<String>> getShips() {
		return ships;
	}
	
	public void setShips(HashMap<Integer, ArrayList<String>> ships) {
		this.ships = ships;
	}
	
	public long getCurrentTime() {
		return currentTime;
	}
	
	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
	}
	
	public long getShipCount() {
		return shipCount;
	}
	
	public void setShipCount(long shipCount) {
		this.shipCount = shipCount;
	}
	
	public void addShip(int id, String vesselClass, Double cog, Double sog, Double lat, Double lon, Byte shipType, Byte navStatus) {
		if (cog == null) {
			cog = 0d;
		}
		if (shipType == null) {
			shipType = 0;
		}
		if (sog == null) {
			sog = 0d;
		}
		
		
		// Round cog to nearest 10
		long cogL = Math.round(cog / 10.0) * 10;
		if (cogL == 360) cogL = 0;
		
		ArrayList<String> list = new ArrayList<String>();		
		
		list.add(Long.toString(cogL));
		list.add(String.format(Locale.US, "%.4f", lat));
		list.add(String.format(Locale.US, "%.4f", lon));
		list.add(vesselClass);
		ShipTypeCargo shipTypeCargo = new ShipTypeCargo(shipType);
		ShipTypeColor color = shipTypeMapper.getColor(shipTypeCargo.getShipType());
		list.add(Integer.toString(color.ordinal()));			
		
		list.add((navStatus != null && (navStatus == 1 || navStatus ==5)) ? "1" : "0");
		
		ships.put(id, list);	
		shipCount++;
	}

}
