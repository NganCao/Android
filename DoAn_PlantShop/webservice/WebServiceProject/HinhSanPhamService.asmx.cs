using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;

namespace WebServiceProject
{
    /// <summary>
    /// Summary description for HinhSanPhamService
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class HinhSanPhamService : System.Web.Services.WebService
    {
        private DataClassesTreeDataContext db = new DataClassesTreeDataContext();

        [WebMethod(Description="Danh sach hinh cac san pham")]
        public List<HinhSanPham> GetListHinhSP()
        {
            List<HinhSanPham> list = new List<HinhSanPham>();
            var query = from sp in db.SanPhams
                        join d in db.HinhSanPhams
                            on new { sp.MaSP } equals new { d.MaSP } into details
                        from d in details
                        select new { sp.MaSP, d.MaHinh, d.LinkHinh };
            foreach (var item in query)
            {
                HinhSanPham hinhSP = new HinhSanPham();
                hinhSP.MaSP = item.MaSP;
                hinhSP.MaHinh = item.MaHinh;
                hinhSP.LinkHinh = item.LinkHinh;
                list.Add(hinhSP);
            }
            return list;
        }

        [WebMethod(Description = "Danh sach hinh sp tu masp")]
        public List<HinhSanPham> GetListHinhSPByMaSP(int masp)
        {
            List<HinhSanPham> list = GetListHinhSP();
            List<HinhSanPham> temp = new List<HinhSanPham>();
            foreach (HinhSanPham h in list)
            {
                if (h.MaSP == masp)
                {
                    temp.Add(h);
                }
            }
            return temp;
        }

        [WebMethod(Description = "Dem so hinh sp tu masp")]
        public int CountHinhSPByMaSP(int masp)
        {
            List<HinhSanPham> list = GetListHinhSPByMaSP(masp);
            return list.Count();
        }
    }
}
