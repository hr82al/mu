package ru.haval.action;

import javafx.beans.property.SimpleStringProperty;
import ru.haval.filter.WrTable;

public class hmmr_pm_model implements WrTable
{
	public SimpleStringProperty Id = new SimpleStringProperty();
	public SimpleStringProperty user_id = new SimpleStringProperty();
	//hmmr_pm.Instruction_num
	public SimpleStringProperty num_instruction = new SimpleStringProperty();
	public SimpleStringProperty eq_id = new SimpleStringProperty();
	public SimpleStringProperty Group_PM = new SimpleStringProperty();
	public SimpleStringProperty Line_Machine = new SimpleStringProperty();
	public SimpleStringProperty Operation_Station = new SimpleStringProperty();
    //hmmr_work_recording..Equipment_Full The shot ID of equipment
	public SimpleStringProperty Equipment = new SimpleStringProperty();
	public SimpleStringProperty Group_EQ = new SimpleStringProperty();
	public SimpleStringProperty Station_Supplier = new SimpleStringProperty();
	public SimpleStringProperty Model_Type_Task = new SimpleStringProperty();
	public SimpleStringProperty PM_Name = new SimpleStringProperty();
	//hmmr_group_cycle.PM_Cycle ID of the cycle execution for example "TO3"
	public SimpleStringProperty PM_Cycle = new SimpleStringProperty();
	public SimpleStringProperty Type_of_PM_Org_Tech = new SimpleStringProperty();
	//hmmr_pm.PM_Resp The ID of the one who control execution of this PM
	public SimpleStringProperty Otv = new SimpleStringProperty();
	public SimpleStringProperty OnOff_Line = new SimpleStringProperty();
	//hmmr_pm.PM_Executor The ID of the worker who do this task.
	public SimpleStringProperty Otv_Isp = new SimpleStringProperty();
	
	public hmmr_pm_model()
	{
		
	}
	
	public hmmr_pm_model(String Id, String user_id, String num_instruction, String eq_id, String Group_PM, String Line_Machine, String Operation_Station, String Equipment,
			             String Group_EQ, String Station_Supplier, String Model_Type_Task, String PM_Name, String PM_Cycle,
			             String Type_of_PM_Org_Tech, String OnOff_Line, String Otv, String Otv_Isp)
	{
		this.Id.set(Id);
		this.user_id.set(user_id);
		this.num_instruction.set(num_instruction);
		this.eq_id.set(eq_id);
		this.Group_PM.set(Group_PM);
		this.Line_Machine.set(Line_Machine);
		this.Operation_Station.set(Operation_Station);
		this.Equipment.set(Equipment);
		this.Group_EQ.set(Group_EQ);
		this.Station_Supplier.set(Station_Supplier);
		this.Model_Type_Task.set(Model_Type_Task);
		this.PM_Name.set(PM_Name);
		this.PM_Cycle.set(PM_Cycle);
		this.Type_of_PM_Org_Tech.set(Type_of_PM_Org_Tech);
		this.OnOff_Line.set(OnOff_Line);
		this.Otv.set(Otv);
		this.Otv_Isp.set(Otv_Isp);
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

    public String getuser_id() {
        return user_id.get();
    }

    public SimpleStringProperty user_idProperty()
    {
    	return user_id;
    }
    
    public void setuser_id(String user_id) {
        this.user_id.set(user_id);
    }

    
    public String getnum_inst() {
        return num_instruction.get();
    }

    public SimpleStringProperty num_instProperty()
    {
    	return num_instruction;
    }
    
    public void setnum_inst(String num_instruction) {
        this.num_instruction.set(num_instruction);
    }
    
    public String geteq_id() {
        return eq_id.get();
    }

    public SimpleStringProperty eq_idProperty()
    {
    	return eq_id;
    }
    
    public void seteq_id(String eq_id) {
        this.eq_id.set(eq_id);
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
    
    public String getL_M() {
        return Line_Machine.get();
    }

    public SimpleStringProperty L_MProperty()
    {
    	return Line_Machine;
    }
    
    public void setL_M(String Line_Machine) {
        this.Line_Machine.set(Line_Machine);
    }
    
    public String getO_S() {
        return Operation_Station.get();
    }

    public SimpleStringProperty O_SProperty()
    {
    	return Operation_Station;
    }
    
    public void setO_S(String Opeeration_Station) {
        this.Operation_Station.set(Opeeration_Station);
    }

    public String getEquip() {
        return Equipment.get();
    }

    public SimpleStringProperty EquipProperty()
    {
    	return Equipment;
    }
    
    public void setEquip(String Equipment) {
        this.Equipment.set(Equipment);
    }

    public String getGroup_EQ() {
        return Group_EQ.get();
    }

    public SimpleStringProperty Group_EQProperty()
    {
    	return Group_EQ;
    }
    
    public void setGroup_EQ(String Group_EQ) {
        this.Group_EQ.set(Group_EQ);
    }

    public String getS_S() {
        return Station_Supplier.get();
    }

    public SimpleStringProperty S_SProperty()
    {
    	return Station_Supplier;
    }
    
    public void setS_S(String Station_Supplier) {
        this.Station_Supplier.set(Station_Supplier);
    }

    public String getMTT() {
        return Model_Type_Task.get();
    }

    public SimpleStringProperty MTTProperty()
    {
    	return Model_Type_Task;
    }
    
    public void setMTT(String Model_Type_Task) {
        this.Model_Type_Task.set(Model_Type_Task);
    }

    public String getPMN() {
        return PM_Name.get();
    }

    public SimpleStringProperty PMNProperty()
    {
    	return PM_Name;
    }
    
    public void setPMN(String PM_Name) {
        this.PM_Name.set(PM_Name);
    }

    public String getPMC() {
        return PM_Cycle.get();
    }

    public SimpleStringProperty PMCProperty()
    {
    	return PM_Cycle;
    }
    
    public void setPMC(String PM_Cycle) {
        this.PM_Cycle.set(PM_Cycle);
    }

    public String getTPOT() {
        return Type_of_PM_Org_Tech.get();
    }

    public SimpleStringProperty TPOTProperty()
    {
    	return Type_of_PM_Org_Tech;
    }
    
    public void setTPOT(String Type_of_PM_Org_Tech) {
        this.Type_of_PM_Org_Tech.set(Type_of_PM_Org_Tech);
    }

    public String getOOL() {
        return OnOff_Line.get();
    }

    public SimpleStringProperty OOLProperty()
    {
    	return OnOff_Line;
    }
    
    public void setOOL(String OnOff_Line) {
        this.OnOff_Line.set(OnOff_Line);
    }
    
    public String getOtv() {
        return Otv.get();
    }

    public SimpleStringProperty OtvProperty()
    {
    	return Otv;
    }
    
    public void setOtv(String Otv) {
        this.Otv.set(Otv);
    }
    
    public String getOtv_Isp() {
        return Otv_Isp.get();
    }

    public SimpleStringProperty Otv_IspProperty()
    {
    	return Otv_Isp;
    }
    
    public void setOtv_Isp(String Otv_Isp) {
        this.Otv_Isp.set(Otv_Isp);
    }

    @Override
    public String toString() {
        return "hmmr_pm_model{" +
               "Id=" + Id.get() +
               ", user_id='" + user_id.get() + '\'' +
                ", num_instruction='" + num_instruction.get() + '\'' +
                ", eq_id='" + eq_id.get() + '\'' +
                ", Group_PM='" + Group_PM.get() + '\'' +
                ", Line_Machine='" + Line_Machine.get() + '\'' +
                ", operation_Station='" + Operation_Station.get() + '\'' +
                ", Equipment='" + Equipment.get() + '\'' +
                ", Group_EQ='" + Group_EQ.get() + '\'' +
                ", Station_Supplier='" + Station_Supplier.get() + '\'' +
                ", Model_Type_Task='" + Model_Type_Task.get() + '\'' +
                ", PM_Name='" + PM_Name.get() + '\'' +
                ", PM_Cycle='" + PM_Cycle.get() + '\'' +
                ", Type_of_PM_Org_Tech='" + Type_of_PM_Org_Tech.get() + '\'' +
                ", OnOff_Line='" + OnOff_Line.get() + '\'' +
                ", Otv='" + Otv.get() + '\'' +
                ", Otv_Isp='" + Otv_Isp.get() + '\'' +
                '}';
    }

    @Override
    public String getap_num() {
        return null;
    }

    @Override
    public String getdata() {
        return null;
    }

    @Override
    public String getshift_report() {
        return OnOff_Line.get();
    }
}