package com.th.thuhien.plantshop.ultil;

import com.th.thuhien.plantshop.model.Menu;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuService {
    private final String NAME_SPACE = "http://tempuri.org/";
    private final String METHOD_NAME_LIST_MENU = "GetListMenu";
    private final String SOAP_ACTION_LIST_MENU = NAME_SPACE + METHOD_NAME_LIST_MENU;
    private final String URL = "http://plantshop.somee.com/Service.asmx?WSDL";

    public List<Menu> getListMenu(){
        List<Menu> list = new ArrayList<Menu>();

        SoapObject request = new SoapObject(NAME_SPACE, METHOD_NAME_LIST_MENU);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        try {
            httpTransportSE.call(SOAP_ACTION_LIST_MENU,envelope);

            SoapObject item = (SoapObject) envelope.getResponse();

            for (int i = 0; i < item.getPropertyCount(); i++){
                SoapObject object = (SoapObject) item.getProperty(i);

                Menu menu = new Menu();
                String maMenu = object.getProperty("MaMenu").toString();
                String tenMenu = object.getProperty("TenMenu").toString();

                menu.setMaMenu(Integer.parseInt(maMenu));
                menu.setTenMenu(tenMenu);

                list.add(menu);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return list;
    }
}