using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;

namespace WebServiceProject
{
    /// <summary>
    /// Summary description for SanPhamService
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class SanPhamService : System.Web.Services.WebService
    {
        private DataClassesTreeDataContext db = new DataClassesTreeDataContext();

        // San pham
        [WebMethod(Description = "Dem so san pham")]
        public int CountSanPham()
        {
            List<SanPham> list = db.SanPhams.ToList();
            return list.Count();
        }

        [WebMethod(Description = "Liet ke san pham")]
        public List<SanPham> GetListSanPham()
        {
            List<SanPham> list = db.SanPhams.ToList();
            foreach (SanPham s in list)
            {
                s.Menu = null;
                s.CT_DDHs.Clear();
                s.HinhSanPhams.Clear();
            }
            return list;
            //return db.SanPhams.ToList();
        }

        [WebMethod(Description = "Liet ke san pham theo menu")]
        public List<SanPham> GetListSanPhamTheoMenu(int mamenu)
        {
            List<SanPham> list = db.SanPhams.Where(x => x.MaMenu == mamenu).ToList();
            foreach (SanPham s in list)
            {
                s.MaMenu = null;
                s.CT_DDHs.Clear();
                s.HinhSanPhams.Clear();
            }
            return list;
        }

        [WebMethod(Description = "Dem so san pham theo menu")]
        public int CountSanPhamByMenu(int mamenu)
        {
            List<SanPham> list = db.SanPhams.Where(x => x.MaMenu == mamenu).ToList();
            return list.Count();
        }

        [WebMethod(Description = "Liet ke n san pham moi nhat")]
        public List<SanPham> GetListSanPhamMoiNhat(int n)
        {
            //List<SanPham> list = db.SanPhams.OrderByDescending(x => x.MaSP).Take(n).ToList();
            List<SanPham> list = db.SanPhams.OrderByDescending(x => x.MaSP).Take(n).ToList();
            foreach (SanPham s in list)
            {
                s.CT_DDHs.Clear();
                s.Menu = null;
                s.HinhSanPhams.Clear();
            }
            
            return list;
        }

        [WebMethod(Description = "Sap xep san pham moi nhat")]
        public List<SanPham> FilterListSanPhamMoiNhat()
        {

            List<SanPham> list = db.SanPhams.OrderByDescending(x => x.MaSP).ToList();
            foreach (SanPham s in list)
            {
                s.CT_DDHs.Clear();
                s.Menu = null;
                s.HinhSanPhams.Clear();
            }

            return list;
        }

        [WebMethod(Description = "Kiem tra sp co ton tai hay khong")]
        public int KiemTraSP(int masp)
        {
            SanPham s = db.SanPhams.FirstOrDefault(x => x.MaSP == masp);
            if (s == null)
            {
                return 0;
            }
            return 1;
        }

        [WebMethod(Description = "Tim san pham theo id")]
        public SanPham GetSanPham(int maSP)
        {
            
            SanPham s = db.SanPhams.Single(sp => sp.MaSP == maSP);
            
            s.Menu = null;
            s.CT_DDHs.Clear();
            s.HinhSanPhams.Clear();
            return s;
            
            
             //return db.SanPhams.Single(sp => sp.MaSP == maSP);
        }

        [WebMethod(Description = "Tim san pham theo ten san pham")]
        public int GetMaSPByName(string name)
        {
            SanPham s = db.SanPhams.Single(x => x.TenSP == name);
            
            s.Menu = null;
            s.CT_DDHs.Clear();
            s.HinhSanPhams.Clear();
            
            return s.MaSP;
        }

        [WebMethod(Description = "Tim kiem san pham theo ten san pham")]
        public List<SanPham> GetListSanPhamByName(string name)
        {
            List<SanPham> list = db.SanPhams.Where(x => x.TenSP.Contains(name)).ToList();
            foreach (SanPham s in list)
            {
                s.Menu = null;
                s.CT_DDHs.Clear();
                s.HinhSanPhams.Clear();
            }
            return list;
        }

        [WebMethod(Description = "3 san pham cung loai - nhap n (số sp), mamenu, masp")]
        public List<SanPham> Get3SPCungLoai(int n, int mamenu, int masp)
        {
            List<SanPham> list = db.SanPhams.Where(x => x.MaMenu == mamenu && x.MaSP != masp).ToList();
            List<SanPham> temp = new List<SanPham>();
            int i = 0;
            foreach (SanPham s in list)
            {
                if (i == n)
                {
                    break;
                }
                else
                {
                    s.Menu = null;
                    s.CT_DDHs.Clear();
                    s.HinhSanPhams.Clear();
                    temp.Add(s);
                    i++;
                }
            }
            return temp;
        }

        //[WebMethod(Description = "Loc san pham theo GIA MIN")]
        //public List<SanPham> FilterSanPhamByMinPrice(int minGia)
        //{
        //    //bool i = false;
        //    List<SanPham> list = db.SanPhams.ToList();
            
        //    List<SanPham> listtemp = new List<SanPham> { };
            
            
        //        foreach (SanPham s in list)
        //        {
        //            if (s.Gia >= minGia)
        //            {
        //                s.Menu = null;
        //                s.CT_DDHs.Clear();
        //                listtemp.Add(s);
        //            }
        //        }
        //        list.Clear();
           
        //    return listtemp;
        //}

        //[WebMethod(Description = "Loc san pham theo GIA MAX")]
        //public List<SanPham> FilterSanPhamByMaxPrice(int maxGia)
        //{
        //    //bool i = false;
        //    List<SanPham> list = db.SanPhams.ToList();

        //    List<SanPham> listtemp = new List<SanPham> { };

            
        //        foreach (SanPham s in list)
        //        {
        //            if (s.Gia <= maxGia)
        //            {
        //                s.Menu = null;
        //                s.CT_DDHs.Clear();
        //                listtemp.Add(s);
        //            }
        //        }
        //        list.Clear();
           
        //    return listtemp;
        //}

        //[WebMethod(Description = "Sap xep san pham theo thoi gian: 0-cu nhat, 1-moi nhat")]
        //public List<SanPham> FilterSanPhamByThoiGian(List<SanPham> list, int thoiGian)
        //{
        //    List<SanPham> data;

        //    if (thoiGian == 1)
        //    {
        //        data = db.SanPhams.OrderByDescending.Take(n).ToList();
        //    }
        //    else
        //    {
        //        data = db.SanPhams.OrderBy()
        //    }

        //    return data;
        //}

        [WebMethod(Description = "Them san pham")]
        public bool InsertSanPham(string name, string img, string infor, int price, int type)
        {
            try
            {
                SanPham m = new SanPham();
                m.TenSP = name;
                m.HinhAnh = img;
                m.ThongTin = infor;
                m.Gia = price;
                m.MaMenu = type;
                db.SanPhams.InsertOnSubmit(m);
                db.SubmitChanges();
                return true;
            }
            catch
            {
                return false;
            }
        }
        [WebMethod(Description = "Xoa san pham")]
        public bool DeleteSanPham(int maSP)
        {
            try
            {
                SanPham m = db.SanPhams.Single(dm => dm.MaSP == maSP);
                db.SanPhams.DeleteOnSubmit(m);
                db.SubmitChanges();
                return true;
            }
            catch
            {
                return false;
            }
        }
        [WebMethod(Description = "Cap nhat san pham")]
        public bool UpdateSanPham(int maSP, string name, string img, string infor, int price, int type)
        {
            try
            {
                SanPham m = db.SanPhams.Single(dm => dm.MaSP == maSP);
                m.TenSP = name;
                m.HinhAnh = img;
                m.ThongTin = infor;
                m.Gia = price;
                m.MaMenu = type;
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
