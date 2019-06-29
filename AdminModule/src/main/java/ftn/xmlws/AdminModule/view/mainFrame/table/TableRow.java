package ftn.xmlws.AdminModule.view.mainFrame.table;

import java.util.ArrayList;
import java.util.List;

public class TableRow {

	private List<Object> listOfObjects;

	public TableRow() {
		this.listOfObjects = new ArrayList<Object>();
	}

	public List<Object> getListOfObjects() {
		return listOfObjects;
	}

	public void setListOfObjects(List<Object> listOfObjects) {
		this.listOfObjects = listOfObjects;
	}

}
