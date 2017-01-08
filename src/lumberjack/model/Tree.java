package lumberjack.model;

public class Tree {
	int heightH;
	int thicknessD;
	int unitWeightC;
	int unitValueP;
	
	

	public Tree(int heightH, int thicknessD, int weightC, int valueP) {
		super(); // zapomniałem już co oznaczał ten super.
		this.heightH = heightH;
		this.thicknessD = thicknessD;
		this.unitWeightC = weightC;
		this.unitValueP = valueP;
	}
	
	@Override
	public String toString() {
		return "Tree [heightH=" + heightH + ", thicknessD=" + thicknessD + ", weightC=" + unitWeightC + ", valueP=" + unitValueP
				+ "]";
	}

	public int getHeightH() {
		return heightH;
	}
	public void setHeightH(int heightH) {
		this.heightH = heightH;
	}
	
	public int getThicknessD() {
		return thicknessD;
	}
	public void setThicknessD(int thicknessD) {
		this.thicknessD = thicknessD;
	}


	public int getUnitWeightC() {
		return unitWeightC;
	}

	public void setUnitWeightC(int unitWeightC) {
		this.unitWeightC = unitWeightC;
	}

	public int getUnitValueP() {
		return unitValueP;
	}

	public void setUnitValueP(int unitValueP) {
		this.unitValueP = unitValueP;
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
	
}
