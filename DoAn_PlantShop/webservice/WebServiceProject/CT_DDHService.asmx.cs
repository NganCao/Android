using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;

namespace WebServiceProject
{
    /// <summary>
    /// Summary description for CT_DDHService
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class CT_DDHService : System.Web.Services.WebService
    {
        private DataClassesTreeDataContext db = new DataClassesTreeDataContext();

        // Chi tiet DDH
        [WebMethod(Description = "Liet ke ct_ddh")]
        public List<CT_DDH> GetListCT_DDH()
        {
            List<CT_DDH> list = new List<CT_DDH> { };
            var query = from ord in db.DonDatHangs
                        from pro in db.SanPhams
                        join d in db.CT_DDHs
                            on new { ord.MaDDH, pro.MaSP } equals new { d.MaDDH, d.MaSP } into details
                        from d in details
                        select new { ord.MaDDH, pro.MaSP, d.DonGia, d.SoLuong };

            //List<CT_DDH> list = db.CT_DDHs.ToList();
            //foreach (CT_DDH ct in list)
            //{
            //    ct.SanPham = null;
            //    ct.DonDatHang = null;
            //}
            foreach (var item in query)
            {
                CT_DDH ct = new CT_DDH();
                ct.MaSP = item.MaSP;
                ct.MaDDH = item.MaDDH;
                ct.DonGia = item.DonGia;
                ct.SoLuong = item.SoLuong;
                list.Add(ct);
            }
            return list;
            //return db.CT_DDHs.ToList();
        }

        // lấy danh sách sản phẩm theo trong ct_ddh theo maDDH => CHƯA LÀM ĐƯỢC


        [WebMethod(Description = "Tim ct_ddh theo id")]
        public CT_DDH GetCT_DDH(int maDDH, int masp)
        {
            return db.CT_DDHs.FirstOrDefault(d => d.MaDDH == maDDH && d.MaSP == masp);
        }

        [WebMethod(Description = "Tim ct_ddh theo id")]
        public List<CT_DDH> GetCT_DDHByMaDDH(int maDDH)
        {
            List<CT_DDH> list = GetListCT_DDH();
            List<CT_DDH> test = new List<CT_DDH>();
            foreach (CT_DDH ct in list)
            {
                if (ct.MaDDH == maDDH)
                {
                    test.Add(ct);
                }
            }
            return test;
        }

        //DANG SAI -> SUA NHE

        //[WebMethod]
        //public SanPham getGia(int masp)
        //{
        //    SanPham sp = db.SanPhams.Single(x => x.MaSP == masp);
        //    return sp;
        //}
        [WebMethod(Description = "Them ct_ddh")]
        public bool InsertCT_DDH(int maddh, int masp, int soluong, int dongia)
        {
            try
            {

                CT_DDH m = new CT_DDH();


                m.MaDDH = maddh;
                m.MaSP = masp;
                m.SoLuong = soluong;
                m.DonGia = dongia;
                db.CT_DDHs.InsertOnSubmit(m);
                db.SubmitChanges();
                return true;   // thêm thành công

            }
            catch
            {
                return false;
            }

        }

        [WebMethod(Description = "Xoa CT_ddh")]
        public bool DeleteCT_DDH(int maddh, int masp)
        {
            try
            {
                CT_DDH m = db.CT_DDHs.Single(ddh => ddh.MaDDH == maddh && ddh.MaSP == masp);
                db.CT_DDHs.DeleteOnSubmit(m);
                db.SubmitChanges();
                return true;
            }
            catch
            {
                return false;
            }
        }
        [WebMethod(Description = "Cap nhat ct_ddh")]
        public bool UpdateCT_DDH(int maddh, int masp, int soluong, int dongia)
        {
            try
            {
                CT_DDH m = db.CT_DDHs.Single(ddh => ddh.MaDDH == maddh && ddh.MaSP == masp);
                m.SoLuong = soluong;
                m.DonGia = dongia;
                db.SubmitChanges();
                return true;
            }
            catch
            {
                return false;
            }
        }
    }
}
