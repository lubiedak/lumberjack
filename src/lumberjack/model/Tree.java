package lumberjack.model;

public class Tree {
	
	private final int id;
	
	private final int heightH;
	private final int thicknessD;
	private final int unitWeightC;
	private final int unitValueP;
	private final int x;
	private final int y;
	
	private boolean cut;
	

	
	public Tree(int id, int heightH, int thicknessD, int weightC, int valueP, int x, int y) {
		super();
		this.id = id;
		this.heightH = heightH;
		this.thicknessD = thicknessD;
		this.unitWeightC = weightC;
		this.unitValueP = valueP;
		this.x = x;
		this.y = y;
		this.cut = false;
	}

	@Override
	public String toString() {
		return "Tree [id=" + id + ", heightH=" + heightH + ", thicknessD=" + thicknessD + ", unitWeightC=" + unitWeightC
				+ ", unitValueP=" + unitValueP + ", x=" + x + ", y=" + y + ", cut=" + cut + "]";
	}

	public int getId() {
		return id;
	}

	public int getHeightH() {
		return heightH;
	}
	
	public int getThicknessD() {
		return thicknessD;
	}

	public int getUnitWeightC() {
		return unitWeightC;
	}

	public int getUnitValueP() {
		return unitValueP;
	}
	
	public int getTreeValue() {
		return unitValueP*heightH*thicknessD;
	}
	
	public int getTreeWeight() {
		return unitWeightC*heightH*thicknessD;
	}
	
	public int getTimeNeededToCut() {
		return thicknessD;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void cutTree(){
		cut = true;
	}
	
	public boolean isCut(){
		return cut;
	}
}
