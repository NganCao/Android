package com.th.thuhien.plantshop.ultil;

import android.util.Log;

import com.th.thuhien.plantshop.model.HinhSanPham;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HinhSanPhamService {
    private final String NAME_SPACE = "http://tempuri.org/";
    private final String METHOD_NAME_HINH_BY_MASP = "GetListHinhSPByMaSP ";

    private final String SOAP_ACTION_HINH_BY_MASP = NAME_SPACE + METHOD_NAME_HINH_BY_MASP;

    private final String URL = "http://plantshop.somee.com/HinhSanPhamService.asmx?WSDL";

    public List<HinhSanPham> getListHinhByMaSp(int masp){
        Log.d("maspHinh: ", String.valueOf(masp));
        List<HinhSanPham> list = new ArrayList<>();
        SoapObject request = new SoapObject(NAME_SPACE, METHOD_NAME_HINH_BY_MASP);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        request.addProperty("masp", masp);

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_ACTION_HINH_BY_MASP, envelope);
            SoapObject object = (SoapObject) envelope.getResponse();
            for (int i = 0; i < object.getPropertyCount(); i++){
                SoapObject item = (SoapObject) object.getProperty(i);
                HinhSanPham hinhSanPham = new HinhSanPham();
                String maSP = item.getProperty("MaSP").toString();
                String maHinh = item.getProperty("MaHinh").toString();
                String linkHinh = item.getProperty("LinkHinh").toString();

                hinhSanPham.setMaSp(Integer.parseInt(maSP));
                hinhSanPham.setMaHinh(Integer.parseInt(maHinh));
                hinhSanPham.setUrlHinh(linkHinh);

                list.add(hinhSanPham);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        return list;
    }
}
