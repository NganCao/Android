package com.th.thuhien.plantshop.ultil;

import com.th.thuhien.plantshop.model.DonDatHang;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DDH {
    private final String NAME_SPACE = "http://tempuri.org/";
    private final String METHOD_NAME = "GetMaDDH_InsertDDH";
    private final String METHOD_NAME_CT_DDH = "InsertCT_DDH";
    private final String METHOD_LIST_DDH = "GetListDDH";

    private final String SOAP_ACTION_CT_DDH = NAME_SPACE + METHOD_NAME_CT_DDH;
    private final String SOAP_ACTION = NAME_SPACE + METHOD_NAME;
    private final String SOAP_ACTION_LIST_DDH = NAME_SPACE + METHOD_LIST_DDH;

    private final String URL = "http://plantshop.somee.com/Service.asmx?WSDL";

    public Integer insertDDH(String tenKH, String sdt, String email, String diaChi)
    {
        int result = 0;
        try {
            HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
            SoapObject request = new SoapObject(NAME_SPACE, METHOD_NAME);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            request.addProperty("tenKH", tenKH);
            request.addProperty("sdt", sdt);
            request.addProperty("email", email);
            request.addProperty("diachi", diaChi);
            envelope.setOutputSoapObject(request);

            httpTransportSE.call(SOAP_ACTION, envelope);
            SoapPrimitive s = (SoapPrimitive) envelope.getResponse();

            String string = s.toString();
            result = Integer.parseInt(string);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    public Integer insertCT_DDH(int maddh, int masp, int soluong, int gia)
    {
        int result=0;
        try {
            HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
            SoapObject request = new SoapObject(NAME_SPACE, METHOD_NAME_CT_DDH);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            request.addProperty("maddh", maddh);
            request.addProperty("masp", masp);
            request.addProperty("soluong", soluong);
            request.addProperty("dongia", gia);
            envelope.setOutputSoapObject(request);

            httpTransportSE.call(SOAP_ACTION_CT_DDH, envelope);
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

    public List<DonDatHang> getListDDH(){
        List<DonDatHang> list = new ArrayList<>();
        SoapObject request = new SoapObject(NAME_SPACE, METHOD_LIST_DDH);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_ACTION_LIST_DDH, envelope);

            SoapObject object = (SoapObject) envelope.getResponse();
            for (int i = 0; i < object.getPropertyCount(); i++){
                SoapObject item = (SoapObject) object.getProperty(i);
                DonDatHang donDatHang = new DonDatHang();
                String maDDH = item.getProperty("MaDDH").toString();
                String tenKH = item.getProperty("TenKhachHang").toString();
                String sdtKH = item.getProperty("SDT").toString();
                String emailKH = item.getProperty("Email").toString();
                String diachiKH = item.getProperty("DiaChi").toString();

                donDatHang.setMaDDH(Integer.parseInt(maDDH));
                donDatHang.setTenKH(tenKH);
                donDatHang.setSdtKH(sdtKH);
                donDatHang.setEmailKH(emailKH);
                donDatHang.setDiachiKH(diachiKH);
                list.add(donDatHang);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return list;
    }
}
