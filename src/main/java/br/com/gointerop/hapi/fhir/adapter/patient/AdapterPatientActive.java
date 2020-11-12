package br.com.gointerop.hapi.fhir.adapter.patient;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.gointerop.hapi.fhir.adapter.Adapter;
import br.com.gointerop.hapi.fhir.adapter.IAdapter;
import br.com.gointerop.hapi.fhir.mapper.MapperPatient;

public class AdapterPatientActive extends Adapter<String> {
	private static IAdapter<String> instance;

	public static IAdapter<String> getInstance() {
		if (AdapterPatientActive.instance == null) {
			AdapterPatientActive.instance = new AdapterPatientActive();
		}

		return AdapterPatientActive.instance;
	}

	@Override
	public String mapRow(ResultSet rs, int rownumber) throws SQLException {
		String retVal = "false";
		int indexActive = -1, valueActive = -1;

		try {
			indexActive = rs.findColumn(MapperPatient.active);
		} catch (SQLException e) {
		}

		if (indexActive > -1)
			valueActive = rs.getInt(indexActive);

		switch (valueActive) {
			case 1:
				retVal = Boolean.TRUE.toString();
				break;
			default:
				retVal = Boolean.FALSE.toString();
		}

		return retVal;
	}
}
