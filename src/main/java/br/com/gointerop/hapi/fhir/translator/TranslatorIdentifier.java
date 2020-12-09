package br.com.gointerop.hapi.fhir.translator;

import java.util.HashMap;

import org.hl7.fhir.valueset.Patient;

import br.com.gointerop.hapi.fhir.util.UtilBaseParam;
import br.gov.saude.namingsystem.CPF;
import ca.uhn.fhir.rest.param.BaseParam;

public class TranslatorIdentifier extends Translator {
	private static final String FIELD_IDENTIFIER = "identifier";

	@Override
	public HashMap<String, BaseParam> translate(HashMap<String, BaseParam> params) {
		HashMap<String, BaseParam> retVal = new HashMap<String, BaseParam>();

		for (String key : params.keySet()) {
			String newKey = null;
			BaseParam value = params.get(key);

			if (value == null)
				continue;

			switch (key) {
				case FIELD_IDENTIFIER:
					newKey = translateIdentifier(UtilBaseParam.toSystem(value));
					break;
			}
			if (newKey != null) {
				retVal.put(newKey, value);
			} else {
				retVal.put(key, value);
			}
		}

		return retVal;
	}

	public String translateIdentifier(String value) {
		String retVal = null;

		switch (value) {
			case CPF.URL:
				retVal = "identifier_cpf";
				break;
		}

		return retVal;
	}
}
