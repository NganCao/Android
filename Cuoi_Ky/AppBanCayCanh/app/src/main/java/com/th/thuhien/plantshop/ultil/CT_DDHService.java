package com.th.thuhien.plantshop.ultil;

import com.th.thuhien.plantshop.model.CT_DDH;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.List;

public class CT_DDHService {
    private final String NAME_SPACE = "http://tempuri.org/";
    private final String METHOD_NAME = "InsertCT_DDH";
    private final String METHOD_NAME_GET = "GetMaDDHMax";
    private final String METHOD_NAME_CTDDH_BY_MADDH = "GetCT_DDHByMaDDH ";

    private final String SOAP_ACTION_GET = NAME_SPACE + METHOD_NAME_GET;
    private final String SOAP_ACTION = NAME_SPACE + METHOD_NAME;
    private final String SOAP_ACTION_CTDDH_BY_MADDH = NAME_SPACE + METHOD_NAME_CTDDH_BY_MADDH;

    private final String URL = "http://plantshop.somee.com/CT_DDHService.asmx?WSDL";

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

    public List<CT_DDH> getListCTDDHByMaDD(int maDDH){
        List<CT_DDH> list = new ArrayList<>();
        SoapObject request = new SoapObject(NAME_SPACE, METHOD_NAME_CTDDH_BY_MADDH);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        request.addProperty("maDDH", maDDH);

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_ACTION_CTDDH_BY_MADDH, envelope);
            SoapObject object = (SoapObject) envelope.getResponse();
            for (int i = 0; i < object.getPropertyCount(); i++){
                SoapObject item = (SoapObject) object.getProperty(i);

                CT_DDH ct_ddh = new CT_DDH();
                String maddh = item.getProperty("MaDDH").toString();
                String masp = item.getProperty("MaSP").toString();
                String soluong = item.getProperty("SoLuong").toString();
                String dongia = item.getProperty("DonGia").toString();

                ct_ddh.setMaDDH(Integer.parseInt(maddh));
                ct_ddh.setMaSP(Integer.parseInt(masp));
                ct_ddh.setSoluong(Integer.parseInt(soluong));
                ct_ddh.setDongia(Integer.parseInt(dongia));

                list.add(ct_ddh);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        return list;
    }
}
