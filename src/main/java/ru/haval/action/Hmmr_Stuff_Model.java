package ru.haval.action;

import javafx.beans.property.SimpleStringProperty;

public class Hmmr_Stuff_Model {

	public SimpleStringProperty id = new SimpleStringProperty();
	public SimpleStringProperty StaffId = new SimpleStringProperty();
	public SimpleStringProperty ID = new SimpleStringProperty();
	public SimpleStringProperty user_id = new SimpleStringProperty();
	public SimpleStringProperty L_Name_Rus = new SimpleStringProperty();
	public SimpleStringProperty F_Name_Rus = new SimpleStringProperty();
	public SimpleStringProperty Otchestvo = new SimpleStringProperty();
	public SimpleStringProperty First_Name = new SimpleStringProperty();
	public SimpleStringProperty Last_Name = new SimpleStringProperty();
	public SimpleStringProperty DoB = new SimpleStringProperty();
	public SimpleStringProperty Sec = new SimpleStringProperty();
	public SimpleStringProperty Group_S = new SimpleStringProperty();
	public SimpleStringProperty Team = new SimpleStringProperty();
	public SimpleStringProperty WorkShift = new SimpleStringProperty();
	public SimpleStringProperty Position = new SimpleStringProperty();
	public SimpleStringProperty Position_RUS = new SimpleStringProperty();
	public SimpleStringProperty HR = new SimpleStringProperty();
	public SimpleStringProperty GWM_ID = new SimpleStringProperty();
	public SimpleStringProperty Date_of_Start = new SimpleStringProperty();
	public SimpleStringProperty E_Mail = new SimpleStringProperty();
	public SimpleStringProperty Skype = new SimpleStringProperty();
	public SimpleStringProperty Cell_1 = new SimpleStringProperty();
	public SimpleStringProperty Cell_2 = new SimpleStringProperty();
	public SimpleStringProperty Address = new SimpleStringProperty();
	public SimpleStringProperty Avto = new SimpleStringProperty();
	public SimpleStringProperty Shoes_Size = new SimpleStringProperty();
	public SimpleStringProperty Clothes_Size = new SimpleStringProperty();
	public SimpleStringProperty Working = new SimpleStringProperty();
	public SimpleStringProperty Quit_Date = new SimpleStringProperty();
	public SimpleStringProperty Login = new SimpleStringProperty();
	public SimpleStringProperty Passwd = new SimpleStringProperty();
		
	public Hmmr_Stuff_Model()
	{
		
	}
	
	public Hmmr_Stuff_Model(String id, String StaffId, String ID, String user_id, String L_Name_Rus, String F_Name_Rus, String Otchestvo, String First_Name, String Last_Name,
			String DoB, String Sec, String Group_S, String Team, String WorkShift, String Position, String Position_RUS, String HR, String GWM_ID, String Date_of_Start,
			String E_Mail, String Skype, String Cell_1, String Cell_2, String Address, String Avto, String Shoes_Size, String Clothes_Size, String Working, String Quit_Date,
			String Login, String Passwd)
	{
		this.id.set(id);
		this.StaffId.set(StaffId);
		this.ID.set(ID);
		this.user_id.set(user_id);
		this.L_Name_Rus.set(L_Name_Rus);
		this.F_Name_Rus.set(F_Name_Rus);
		this.Otchestvo.set(Otchestvo);
		this.First_Name.set(First_Name);
		this.Last_Name.set(Last_Name);
		this.DoB.set(DoB);
		this.Sec.set(Sec);
		this.Group_S.set(Group_S);
		this.Team.set(Team);
		this.WorkShift.set(WorkShift);
		this.Position.set(Position);
		this.Position_RUS.set(Position_RUS);
		this.HR.set(HR);
		this.GWM_ID.set(GWM_ID);
		this.Date_of_Start.set(Date_of_Start);
		this.E_Mail.set(E_Mail);
		this.Skype.set(Skype);
		this.Cell_1.set(Cell_1);
		this.Cell_2.set(Cell_2);
		this.Address.set(Address);
		this.Avto.set(Avto);
		this.Shoes_Size.set(Shoes_Size);
		this.Clothes_Size.set(Clothes_Size);
		this.Working.set(Working);
		this.Quit_Date.set(Quit_Date);
		this.Login.set(Login);
		this.Passwd.set(Passwd);
	}

	public SimpleStringProperty getId() {
		return id;
	}

	public void setId(SimpleStringProperty id) {
		this.id = id;
	}

	public SimpleStringProperty getStaffId() {
		return StaffId;
	}

	public void setStaffId(SimpleStringProperty staffId) {
		StaffId = staffId;
	}

	public SimpleStringProperty getID() {
		return ID;
	}

	public void setID(SimpleStringProperty iD) {
		ID = iD;
	}

	public SimpleStringProperty getUser_id() {
		return user_id;
	}

	public void setUser_id(SimpleStringProperty user_id) {
		this.user_id = user_id;
	}

	public SimpleStringProperty getL_Name_Rus() {
		return L_Name_Rus;
	}

	public void setL_Name_Rus(SimpleStringProperty l_Name_Rus) {
		L_Name_Rus = l_Name_Rus;
	}

	public SimpleStringProperty getF_Name_Rus() {
		return F_Name_Rus;
	}

	public void setF_Name_Rus(SimpleStringProperty f_Name_Rus) {
		F_Name_Rus = f_Name_Rus;
	}

	public SimpleStringProperty getOtchestvo() {
		return Otchestvo;
	}

	public void setOtchestvo(SimpleStringProperty otchestvo) {
		Otchestvo = otchestvo;
	}

	public SimpleStringProperty getFirst_Name() {
		return First_Name;
	}

	public void setFirst_Name(SimpleStringProperty first_Name) {
		First_Name = first_Name;
	}

	public SimpleStringProperty getLast_Name() {
		return Last_Name;
	}

	public void setLast_Name(SimpleStringProperty last_Name) {
		Last_Name = last_Name;
	}

	public SimpleStringProperty getDoB() {
		return DoB;
	}

	public void setDoB(SimpleStringProperty doB) {
		DoB = doB;
	}

	public SimpleStringProperty getSec() {
		return Sec;
	}

	public void setSec(SimpleStringProperty sec) {
		Sec = sec;
	}

	public SimpleStringProperty getGroup_S() {
		return Group_S;
	}

	public void setGroup_S(SimpleStringProperty group_S) {
		Group_S = group_S;
	}

	public SimpleStringProperty getTeam() {
		return Team;
	}

	public void setTeam(SimpleStringProperty team) {
		Team = team;
	}

	public SimpleStringProperty getWorkShift() {
		return WorkShift;
	}

	public void setWorkShift(SimpleStringProperty workShift) {
		WorkShift = workShift;
	}

	public SimpleStringProperty getPosition() {
		return Position;
	}

	public void setPosition(SimpleStringProperty position) {
		Position = position;
	}

	public SimpleStringProperty getPosition_RUS() {
		return Position_RUS;
	}

	public void setPosition_RUS(SimpleStringProperty position_RUS) {
		Position_RUS = position_RUS;
	}

	public SimpleStringProperty getHR() {
		return HR;
	}

	public void setHR(SimpleStringProperty hR) {
		HR = hR;
	}

	public SimpleStringProperty getGWM_ID() {
		return GWM_ID;
	}

	public void setGWM_ID(SimpleStringProperty gWM_ID) {
		GWM_ID = gWM_ID;
	}

	public SimpleStringProperty getDate_of_Start() {
		return Date_of_Start;
	}

	public void setDate_of_Start(SimpleStringProperty date_of_Start) {
		Date_of_Start = date_of_Start;
	}

	public SimpleStringProperty getE_Mail() {
		return E_Mail;
	}

	public void setE_Mail(SimpleStringProperty e_Mail) {
		E_Mail = e_Mail;
	}

	public SimpleStringProperty getSkype() {
		return Skype;
	}

	public void setSkype(SimpleStringProperty skype) {
		Skype = skype;
	}

	public SimpleStringProperty getCell_1() {
		return Cell_1;
	}

	public void setCell_1(SimpleStringProperty cell_1) {
		Cell_1 = cell_1;
	}

	public SimpleStringProperty getCell_2() {
		return Cell_2;
	}

	public void setCell_2(SimpleStringProperty cell_2) {
		Cell_2 = cell_2;
	}

	public SimpleStringProperty getAddress() {
		return Address;
	}

	public void setAddress(SimpleStringProperty address) {
		Address = address;
	}

	public SimpleStringProperty getAvto() {
		return Avto;
	}

	public void setAvto(SimpleStringProperty avto) {
		Avto = avto;
	}

	public SimpleStringProperty getShoes_Size() {
		return Shoes_Size;
	}

	public void setShoes_Size(SimpleStringProperty shoes_Size) {
		Shoes_Size = shoes_Size;
	}

	public SimpleStringProperty getClothes_Size() {
		return Clothes_Size;
	}

	public void setClothes_Size(SimpleStringProperty clothes_Size) {
		Clothes_Size = clothes_Size;
	}

	public SimpleStringProperty getWorking() {
		return Working;
	}

	public void setWorking(SimpleStringProperty working) {
		Working = working;
	}

	public SimpleStringProperty getQuit_Date() {
		return Quit_Date;
	}

	public void setQuit_Date(SimpleStringProperty quit_Date) {
		Quit_Date = quit_Date;
	}

	public SimpleStringProperty getLogin() {
		return Login;
	}

	public void setLogin(SimpleStringProperty login) {
		Login = login;
	}

	public SimpleStringProperty getPasswd() {
		return Passwd;
	}

	public void setPasswd(SimpleStringProperty passwd) {
		Passwd = passwd;
	}
	
	public String getIdStr() {
		return id.get();
	}
}
