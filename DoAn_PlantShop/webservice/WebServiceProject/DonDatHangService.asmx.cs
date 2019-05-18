using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;

namespace WebServiceProject
{
    /// <summary>
    /// Summary description for DonDatHangService
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class DonDatHangService : System.Web.Services.WebService
    {
        private DataClassesTreeDataContext db = new DataClassesTreeDataContext();

        // Don dat hang
        [WebMethod(Description = "Liet ke ddh")]
        public List<DonDatHang> GetListDDH()
        {
            List<DonDatHang> list = db.DonDatHangs.ToList();
            foreach (DonDatHang dh in list)
            {
                dh.CT_DDHs.Clear();
            }
            return list;

            //return db.DonDatHangs.ToList();
        }

        [WebMethod(Description = "Tim maddh max")]
        public int GetMaDDHMax()
        {
            var result = db.DonDatHangs.Max(x => x.MaDDH);
            return result + 0;
        }

        [WebMethod(Description = "Them DDH, tra ve maddh")]
        public int GetMaDDH_InsertDDH(string tenKH, string sdt, string email, string diachi)
        {
            try
            {
                DonDatHang m = new DonDatHang();
                m.TenKhachHang = tenKH;
                m.SDT = sdt;
                m.Email = email;
                m.DiaChi = diachi;
                db.DonDatHangs.InsertOnSubmit(m);
                db.SubmitChanges();
                return m.MaDDH;
            }
            catch
            {
                return 0;
            }
        }

        [WebMethod(Description = "Tim ddh theo id")]
        public DonDatHang GetDDH(int maDDH)
        {
            return db.DonDatHangs.Single(d => d.MaDDH == maDDH);
        }
        [WebMethod(Description = "Them DDH")]
        public bool InsertDDH(string tenKH, string sdt, string email, string diachi)
        {
            try
            {
                DonDatHang m = new DonDatHang();
                m.TenKhachHang = tenKH;
                m.SDT = sdt;
                m.Email = email;
                m.DiaChi = diachi;
                db.DonDatHangs.InsertOnSubmit(m);
                db.SubmitChanges();
                return true;
            }
            catch
            {
                return false;
            }
        }
        [WebMethod(Description = "Xoa ddh")]
        public bool DeleteDDH(int maddh)
        {
            try
            {
                DonDatHang m = db.DonDatHangs.Single(ddh => ddh.MaDDH == maddh);
                db.DonDatHangs.DeleteOnSubmit(m);
                db.SubmitChanges();
                return true;
            }
            catch
            {
                return false;
            }
        }
        [WebMethod(Description = "Cap nhat ddh")]
        public bool UpdateDDH(int maddh, string tenKH, string sdt, string email, string diachi)
        {
            try
            {
                DonDatHang m = db.DonDatHangs.Single(ddh => ddh.MaDDH == maddh);
                m.TenKhachHang = tenKH;
                m.SDT = sdt;
                m.Email = email;
                m.DiaChi = diachi;
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
