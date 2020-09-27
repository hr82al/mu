package ru.haval.action;

import javafx.beans.property.SimpleStringProperty;

public class hmmr_ps_model {
	
	public SimpleStringProperty Id = new SimpleStringProperty();
	public SimpleStringProperty Company = new SimpleStringProperty();
	public SimpleStringProperty Plant = new SimpleStringProperty();
	public SimpleStringProperty Shop_s = new SimpleStringProperty();
	public SimpleStringProperty Group_PM = new SimpleStringProperty();
	public SimpleStringProperty Line_Machine_s = new SimpleStringProperty();
	public SimpleStringProperty Operation_Station_s = new SimpleStringProperty();
	public SimpleStringProperty Equipment_s = new SimpleStringProperty();
	public SimpleStringProperty Shop = new SimpleStringProperty();
	public SimpleStringProperty FL04_Group_ENG = new SimpleStringProperty();
	public SimpleStringProperty Line_Machine = new SimpleStringProperty();
	public SimpleStringProperty Line_Machine_RUS = new SimpleStringProperty();
	public SimpleStringProperty Operation_Station = new SimpleStringProperty();
	public SimpleStringProperty Operation_Station_RUS = new SimpleStringProperty();
	public SimpleStringProperty Equipment = new SimpleStringProperty();
	public SimpleStringProperty FL03_Shop_RUS = new SimpleStringProperty();
	public SimpleStringProperty FL04_Group_RUS = new SimpleStringProperty();
	public SimpleStringProperty FL07_Equipment_RUS = new SimpleStringProperty();
	public SimpleStringProperty Description = new SimpleStringProperty();
	public SimpleStringProperty Equip_label = new SimpleStringProperty();
	//public SimpleStringProperty Sub_Equipment = new SimpleStringProperty();
	public SimpleStringProperty Station_label = new SimpleStringProperty();
	public SimpleStringProperty manual = new SimpleStringProperty();
	public SimpleStringProperty RespPlannerGroup = new SimpleStringProperty();
	public SimpleStringProperty AssetsInvNum = new SimpleStringProperty();
	public SimpleStringProperty AssetsOsNum = new SimpleStringProperty();
	public SimpleStringProperty AssetsStartDate = new SimpleStringProperty();
	public SimpleStringProperty CostCenter = new SimpleStringProperty();
	public SimpleStringProperty Location = new SimpleStringProperty();
	public SimpleStringProperty Station_Supplier = new SimpleStringProperty();
	public SimpleStringProperty Coordinates = new SimpleStringProperty();
	public SimpleStringProperty Altitude = new SimpleStringProperty();
	public SimpleStringProperty CHAMBER = new SimpleStringProperty();
	public SimpleStringProperty TR_CU = new SimpleStringProperty();
	public SimpleStringProperty TR_CU_Link = new SimpleStringProperty();
	public SimpleStringProperty Hazardous = new SimpleStringProperty();
	public SimpleStringProperty Key_equipment = new SimpleStringProperty();
	public SimpleStringProperty Type = new SimpleStringProperty();
	public SimpleStringProperty S_N = new SimpleStringProperty();
	public SimpleStringProperty Manuf = new SimpleStringProperty();
	public SimpleStringProperty MTC = new SimpleStringProperty();
	public SimpleStringProperty Resp = new SimpleStringProperty();
	public SimpleStringProperty M_Electric = new SimpleStringProperty();
	public SimpleStringProperty M_Air = new SimpleStringProperty();
	public SimpleStringProperty M_Water = new SimpleStringProperty();
	public SimpleStringProperty M_Cold_water = new SimpleStringProperty();
	public SimpleStringProperty M_Hot_water = new SimpleStringProperty();
	public SimpleStringProperty RO_Water = new SimpleStringProperty();
	public SimpleStringProperty M_Gas = new SimpleStringProperty();
	
	public hmmr_ps_model()
	{
		
	}
	
	public hmmr_ps_model(String Id, String Company, String Plant, String Shop_s, String Group_PM, String Line_Machine_s, String Operation_Station_s, String Equipment_s, String Shop,
			String FL04_Group_ENG, String Line_Machine, String Line_Machine_RUS, String Operation_Station, String Operation_Station_RUS, String Equipment, String FL03_Shop_RUS, String FL04_Group_RUS, String FL07_Equipment_RUS, String Description, String Equip_label, 
			String Station_label, String manual, String RespPlannerGroup, String AssetsInvNum, String AssetsOsNum, String AssetsStartDate, String CostCenter, String Location, String Station_Supplier,
			String Coordinates, String Altitude, String CHAMBER, String TR_CU, String TR_CU_Link, String Hazardous, String Key_equipment, String Type, String S_N, String Manuf, String MTC, 
			String Resp, String M_Electric, String M_Air, String M_Water, String M_Cold_water, String M_Hot_water, String RO_Water, String M_Gas)
	{
		this.Id.set(Id);
		this.Company.set(Company);
		this.Plant.set(Plant);
		this.Shop_s.set(Shop_s);
		this.Group_PM.set(Group_PM);
		this.Line_Machine_s.set(Line_Machine_s);
		this.Operation_Station_s.set(Operation_Station_s);
		this.Equipment_s.set(Equipment_s);
		this.Shop.set(Shop);
		this.FL04_Group_ENG.set(FL04_Group_ENG);
		this.Line_Machine.set(Line_Machine);
		this.Line_Machine_RUS.set(Line_Machine_RUS);
		this.Operation_Station.set(Operation_Station);
		this.Operation_Station_RUS.set(Operation_Station_RUS);
		this.Equipment.set(Equipment);
		this.FL03_Shop_RUS.set(FL03_Shop_RUS);
		this.FL04_Group_RUS.set(FL04_Group_RUS);
		this.FL07_Equipment_RUS.set(FL07_Equipment_RUS);
		this.Description.set(Description);
		this.Equip_label.set(Equip_label);
		this.Station_label.set(Station_label);
		this.manual.set(manual);
		this.RespPlannerGroup.set(RespPlannerGroup);
		this.AssetsInvNum.set(AssetsInvNum);
		this.AssetsOsNum.set(AssetsOsNum);
		this.AssetsStartDate.set(AssetsStartDate);
		this.CostCenter.set(CostCenter);
		this.Location.set(Location);
		this.Station_Supplier.set(Station_Supplier);
		this.Coordinates.set(Coordinates);
		this.Altitude.set(Altitude);
		this.CHAMBER.set(CHAMBER);
		this.TR_CU.set(TR_CU);
		this.TR_CU_Link.set(TR_CU_Link);
		this.Hazardous.set(Hazardous);
		this.Key_equipment.set(Key_equipment);
		this.Type.set(Type);
		this.S_N.set(S_N);
		this.Manuf.set(Manuf);
		this.MTC.set(MTC);
		this.Resp.set(Resp);
		this.M_Electric.set(M_Electric);
		this.M_Air.set(M_Air);
		this.M_Water.set(M_Water);
		this.M_Cold_water.set(M_Cold_water);
		this.M_Hot_water.set(M_Hot_water);
		this.RO_Water.set(RO_Water);
		this.M_Gas.set(M_Gas);
	}
	
	public String getId() {
        return Id.get();
    }

    public SimpleStringProperty IdProperty()
    {
    	return Id;
    }
    
    public void setId(String Id) {
        this.Id.set(Id);
    }
    
    public String getCompany() {
        return Company.get();
    }

    public SimpleStringProperty CompanyProperty()
    {
    	return Company;
    }
    
    public void setCompany(String Company) {
        this.Company.set(Company);
    }
    
    public String getPlant() {
        return Plant.get();
    }

    public SimpleStringProperty PlantProperty()
    {
    	return Plant;
    }
    
    public void setPlant(String Plant) {
        this.Plant.set(Plant);
    }
    
    public String getShop_s() {
        return Shop_s.get();
    }

    public SimpleStringProperty Shop_sProperty()
    {
    	return Shop_s;
    }
    
    public void setShop_s(String Shop_s) {
        this.Shop_s.set(Shop_s);
    }
    
    public String getGroup_PM() {
        return Group_PM.get();
    }

    public SimpleStringProperty Group_PMProperty()
    {
    	return Group_PM;
    }
    
    public void setGroup_PM(String Group_PM) {
        this.Group_PM.set(Group_PM);
    }
    
    public String getLine_Machine_s() {
        return Line_Machine_s.get();
    }

    public SimpleStringProperty Line_Machine_sProperty()
    {
    	return Line_Machine_s;
    }
    
    public void setLine_Machine_s(String Line_Machine_s) {
        this.Line_Machine_s.set(Line_Machine_s);
    }
    
    public String getOperation_Station_s() {
        return Operation_Station_s.get();
    }

    public SimpleStringProperty Operation_Station_sProperty()
    {
    	return Operation_Station_s;
    }
    
    public void setOperation_Station_s(String Operation_Station_s) {
        this.Operation_Station_s.set(Operation_Station_s);
    }
    
    public String getEquipment_s() {
        return Equipment_s.get();
    }

    public SimpleStringProperty Equipment_sProperty()
    {
    	return Equipment_s;
    }
    
    public void setEquipment_s(String Equipment_s) {
        this.Equipment_s.set(Equipment_s);
    }
    
    public String getShop() {
        return Shop.get();
    }

    public SimpleStringProperty ShopProperty()
    {
    	return Shop;
    }
    
    public void setShop(String Shop) {
        this.Shop.set(Shop);
    }
    
    public String getLine_Machine() {
        return Line_Machine.get();
    }

    public SimpleStringProperty Line_MachineProperty()
    {
    	return Line_Machine;
    }
    
    public void setLine_Machine(String Line_Machine) {
        this.Line_Machine.set(Line_Machine);
    }
    
    public String getLine_Machine_RUS() {
        return Line_Machine_RUS.get();
    }

    public SimpleStringProperty Line_Machine_RUSProperty()
    {
    	return Line_Machine_RUS;
    }
    
    public void setLine_Machine_RUS(String Line_Machine_RUS) {
        this.Line_Machine_RUS.set(Line_Machine_RUS);
    }
    
    public String getOperation_Station() {
        return Operation_Station.get();
    }

    public SimpleStringProperty Operation_StationProperty()
    {
    	return Operation_Station;
    }
    
    public void setOperation_Station(String Operation_Station) {
        this.Operation_Station.set(Operation_Station);
    }
    
    public String getOperation_Station_RUS() {
        return Operation_Station_RUS.get();
    }

    public SimpleStringProperty Operation_Station_RUSProperty()
    {
    	return Operation_Station_RUS;
    }
    
    public void setOperation_Station_RUS(String Operation_Station_RUS) {
        this.Operation_Station_RUS.set(Operation_Station_RUS);
    }
    
    public String getEquipment() {
        return Equipment.get();
    }

    public SimpleStringProperty EquipmentProperty()
    {
    	return Equipment;
    }
    
    public void setEquipment(String Equipment) {
        this.Equipment.set(Equipment);
    }
    
    public String getDescription() {
        return Description.get();
    }

    public SimpleStringProperty DescriptionProperty()
    {
    	return Description;
    }
    
    public void setDescription(String Description) {
        this.Description.set(Description);
    }
    
    public String getEquip_label() {
        return Equip_label.get();
    }

    public SimpleStringProperty Equip_labelProperty()
    {
    	return Equip_label;
    }
    
    public void setEquip_label(String Equip_label) {
        this.Equip_label.set(Equip_label);
    }
    
    public String getStation_label() {
        return Station_label.get();
    }

    public SimpleStringProperty Station_labelProperty()
    {
    	return Station_label;
    }
    
    public void setStation_label(String Station_label) {
        this.Station_label.set(Station_label);
    }
    
    public String getmanual() {
        return manual.get();
    }

    public SimpleStringProperty manualProperty()
    {
    	return manual;
    }
    
    public void setmanual(String manual) {
        this.manual.set(manual);
    }
    
    public String getLocation() {
        return Location.get();
    }

    public SimpleStringProperty LocationProperty()
    {
    	return Location;
    }
    
    public void setLocation(String Location) {
        this.Location.set(Location);
    }
    
    public String getStation_Supplier() {
        return Station_Supplier.get();
    }

    public SimpleStringProperty Station_SupplierProperty()
    {
    	return Station_Supplier;
    }
    
    public void setStation_Supplier(String Station_Supplier) {
        this.Station_Supplier.set(Station_Supplier);
    }
    
   public String getCoordinates() {
        return Coordinates.get();
    }

    public SimpleStringProperty CoordinatesProperty()
    {
    	return Coordinates;
    }
    
    public void setCoordinates(String Coordinates) {
        this.Coordinates.set(Coordinates);
    }
    
    public String getAltitude() {
        return Altitude.get();
    }

    public SimpleStringProperty AltitudeProperty()
    {
    	return Altitude;
    }
    
    public void setAltitude(String Altitude) {
        this.Altitude.set(Altitude);
    }
    
    public String getCHAMBER() {
        return CHAMBER.get();
    }

    public SimpleStringProperty CHAMBERProperty()
    {
    	return CHAMBER;
    }
    
    public void setCHAMBER(String CHAMBER) {
        this.CHAMBER.set(CHAMBER);
    }
    
    public String getTR_CU() {
        return TR_CU.get();
    }

    public SimpleStringProperty TR_CUProperty()
    {
    	return TR_CU;
    }
    
    public void setTR_CU(String TR_CU) {
        this.TR_CU.set(TR_CU);
    }
    
    public String getTR_CU_Link() {
        return TR_CU_Link.get();
    }

    public SimpleStringProperty TR_CU_LinkProperty()
    {
    	return TR_CU_Link;
    }
    
    public void setTR_CU_Link(String TR_CU_Link) {
        this.TR_CU_Link.set(TR_CU_Link);
    }
    
    public String getHazardous() {
        return Hazardous.get();
    }

    public SimpleStringProperty HazardousProperty()
    {
    	return Hazardous;
    }
    
    public void setHazardous(String Hazardous) {
        this.Hazardous.set(Hazardous);
    }
    
    public String getKey_equipment() {
        return Key_equipment.get();
    }

    public SimpleStringProperty Key_equipmentProperty()
    {
    	return Key_equipment;
    }
    
    public void setKey_equipment(String Key_equipment) {
        this.Key_equipment.set(Key_equipment);
    }
    
    public String getType() {
        return Type.get();
    }

    public SimpleStringProperty TypetProperty()
    {
    	return Type;
    }
    
    public void setType(String Type) {
        this.Type.set(Type);
    }
    
    public String getS_N() {
        return S_N.get();
    }

    public SimpleStringProperty S_NProperty()
    {
    	return S_N;
    }
    
    public void setS_N(String S_N) {
        this.S_N.set(S_N);
    }
    
    public String getManuf() {
        return Manuf.get();
    }

    public SimpleStringProperty ManufProperty()
    {
    	return Manuf;
    }
    
    public void setManuf(String Manuf) {
        this.Manuf.set(Manuf);
    }
    
    public String getMTC() {
        return MTC.get();
    }

    public SimpleStringProperty MTCProperty()
    {
    	return MTC;
    }
    
    public void setMTC(String MTC) {
        this.MTC.set(MTC);
    }
    
    public String getResp() {
        return Resp.get();
    }

    public SimpleStringProperty RespProperty()
    {
    	return Resp;
    }
    
    public void setResp(String Resp) {
        this.Resp.set(Resp);
    }
    
    public String getM_Electric() {
        return M_Electric.get();
    }

    public SimpleStringProperty M_ElectricProperty()
    {
    	return M_Electric;
    }
    
    public void setM_Electric(String M_Electric) {
        this.M_Electric.set(M_Electric);
    }
    
    public String getM_Air() {
        return M_Air.get();
    }

    public SimpleStringProperty M_AirProperty()
    {
    	return M_Air;
    }
    
    public void setM_Air(String M_Air) {
        this.M_Air.set(M_Air);
    }
    
    public String getM_Water() {
        return M_Water.get();
    }

    public SimpleStringProperty M_WaterProperty()
    {
    	return M_Water;
    }
    
    public void setM_Water(String M_Water) {
        this.M_Water.set(M_Water);
    }
    
    public String getM_Cold_water() {
        return M_Cold_water.get();
    }

    public SimpleStringProperty M_Cold_waterProperty()
    {
    	return M_Cold_water;
    }
    
    public void setM_Cold_water(String M_Cold_water) {
        this.M_Cold_water.set(M_Cold_water);
    }
    
    public String getM_Hot_water() {
        return M_Hot_water.get();
    }

    public SimpleStringProperty M_Hot_waterProperty()
    {
    	return M_Hot_water;
    }
    
    public void setM_Hot_water(String M_Hot_water) {
        this.M_Hot_water.set(M_Hot_water);
    }
    
    public String getRO_Water() {
        return RO_Water.get();
    }

    public SimpleStringProperty RO_WaterProperty()
    {
    	return RO_Water;
    }
    
    public void setRO_Water(String RO_Water) {
        this.RO_Water.set(RO_Water);
    }
    
    public String getM_Gas() {
        return M_Gas.get();
    }

    public SimpleStringProperty M_GasProperty()
    {
    	return M_Gas;
    }
    
    public void setM_Gas(String M_Gas) {
        this.M_Gas.set(M_Gas);
    }
    
    public String getFL04_Group_ENG() {
        return FL04_Group_ENG.get();
    }

    public SimpleStringProperty FL04_Group_ENGProperty()
    {
    	return FL04_Group_ENG;
    }
    
    public void setFL04_Group_ENG(String FL04_Group_ENG) {
        this.FL04_Group_ENG.set(FL04_Group_ENG);
    }
    
    public String getFL03_Shop_RUS() {
        return FL03_Shop_RUS.get();
    }

    public SimpleStringProperty FL03_Shop_RUSProperty()
    {
    	return FL03_Shop_RUS;
    }
    
    public void setFL03_Shop_RUS(String FL03_Shop_RUS) {
        this.FL03_Shop_RUS.set(FL03_Shop_RUS);
    }
    
    public String getFL04_Group_RUS() {
        return FL04_Group_RUS.get();
    }

    public SimpleStringProperty FL04_Group_RUSProperty()
    {
    	return FL04_Group_RUS;
    }
    
    public void setFL04_Group_RUS(String FL04_Group_RUS) {
        this.FL04_Group_RUS.set(FL04_Group_RUS);
    }
    
    public String getFL07_Equipment_RUS() {
        return FL07_Equipment_RUS.get();
    }

    public SimpleStringProperty FL07_Equipment_RUSProperty()
    {
    	return FL07_Equipment_RUS;
    }
    
    public void setFL07_Equipment_RUS(String FL07_Equipment_RUS) {
        this.FL07_Equipment_RUS.set(FL07_Equipment_RUS);
    }
    
    public String getRespPlannerGroup() {
        return RespPlannerGroup.get();
    }

    public SimpleStringProperty RespPlannerGroupProperty()
    {
    	return RespPlannerGroup;
    }
    
    public void setRespPlannerGroup(String RespPlannerGroup) {
        this.RespPlannerGroup.set(RespPlannerGroup);
    }
    
    public String getAssetsInvNum() {
        return AssetsInvNum.get();
    }

    public SimpleStringProperty AssetsInvNumProperty()
    {
    	return AssetsInvNum;
    }
    
    public void setAssetsInvNum(String AssetsInvNum) {
        this.AssetsInvNum.set(AssetsInvNum);
    }
    
    public String getAssetsOsNum() {
        return AssetsOsNum.get();
    }

    public SimpleStringProperty AssetsOsNumProperty()
    {
    	return AssetsOsNum;
    }
    
    public void setAssetsOsNum(String AssetsOsNum) {
        this.AssetsOsNum.set(AssetsOsNum);
    }
    
    public String getAssetsStartDate() {
        return AssetsStartDate.get();
    }

    public SimpleStringProperty AssetsStartDateProperty()
    {
    	return AssetsStartDate;
    }
    
    public void setAssetsStartDate(String AssetsStartDate) {
        this.AssetsStartDate.set(AssetsStartDate);
    }
    
    public String getCostCenter() {
        return CostCenter.get();
    }

    public SimpleStringProperty CostCenterProperty()
    {
    	return CostCenter;
    }
    
    public void setCostCenter(String CostCenter) {
        this.CostCenter.set(CostCenter);
    }
}
