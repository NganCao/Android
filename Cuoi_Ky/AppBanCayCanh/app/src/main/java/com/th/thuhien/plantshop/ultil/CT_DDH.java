package com.th.thuhien.plantshop.ultil;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class CT_DDH {

        private final String NAME_SPACE = "http://tempuri.org/";
        private final String METHOD_NAME = "InsertCT_DDH";
        private final String METHOD_NAME_GET = "GetMaDDHMax";

        private final String SOAP_ACTION_GET = NAME_SPACE + METHOD_NAME_GET;
        private final String SOAP_ACTION = NAME_SPACE + METHOD_NAME;

        private final String URL = "http://plantshop.somee.com/Service.asmx?WSDL";

        public Integer insertCT_DDH(int maddh, int masp, int soluong, int gia)
        {
            int result=0;
            try {
                HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
                SoapObject request = new SoapObject(NAME_SPACE, METHOD_NAME);
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                request.addProperty("maddh", maddh);
                request.addProperty("masp", masp);
                request.addProperty("soluong", soluong);
                request.addProperty("dongia", gia);
                envelope.setOutputSoapObject(request);

                httpTransportSE.call(SOAP_ACTION, envelope);
                Object s = envelope.getResponse(); //Get XML Result

                if (s.equals("true")) {
                    result = 1;
                }
                else{
                    result = 0;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
        return result;
    }

    public Integer getMDDMax()
    {
        int result=0;
        try {
            HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
            SoapObject request = new SoapObject(NAME_SPACE, METHOD_NAME_GET);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            httpTransportSE.call(SOAP_ACTION_GET, envelope);
            SoapObject s = (SoapObject) envelope.getResponse(); //Get XML Result

            result = Integer.parseInt(s.getProperty("GetMaDDHMaxResult").toString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
