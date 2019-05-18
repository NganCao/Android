using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;

namespace WebServiceProject
{
    /// <summary>
    /// Summary description for TaiKhoanService
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class TaiKhoanService : System.Web.Services.WebService
    {
        private DataClassesTreeDataContext db = new DataClassesTreeDataContext();

        // Tai khoan
        [WebMethod(Description = "Liet ke tai khoan")]
        public List<TaiKhoan> GetListTaiKhoan()
        {
            return db.TaiKhoans.ToList();
        }

        // lấy danh sách sản phẩm theo trong ct_ddh theo maDDH => CHƯA LÀM ĐƯỢC


        [WebMethod(Description = "Tim tai khoan theo username")]
        public TaiKhoan GetTaiKhoan(string username)
        {
            TaiKhoan tk = db.TaiKhoans.Single(d => d.Username == username);

            return tk;
            //return db.TaiKhoans.Single(d => d.Username == username);
        }


        [WebMethod(Description = "Them tai khoan")]
        public bool InsertTaiKhoan(string username, string pass)
        {
            try
            {

                TaiKhoan tk = new TaiKhoan();
                tk.Username = username;
                tk.Password = pass;

                db.TaiKhoans.InsertOnSubmit(tk);
                db.SubmitChanges();
                return true;   // thêm thành công

            }
            catch
            {
                return false;
            }

        }

        [WebMethod(Description = "Xoa tai khoan")]
        public bool DeleteTaiKhoan(string username)
        {
            try
            {
                TaiKhoan m = db.TaiKhoans.Single(tk => tk.Username == username);
                db.TaiKhoans.DeleteOnSubmit(m);
                db.SubmitChanges();
                return true;
            }
            catch
            {
                return false;
            }
        }
        [WebMethod(Description = "Cap nhat tai khoan")]
        public bool UpdateTaiKhoan(string username, string pass)
        {
            try
            {
                TaiKhoan tk = db.TaiKhoans.Single(t => t.Username == username);
                tk.Username = username;
                tk.Password = pass;

                db.SubmitChanges();
                return true;
            }
            catch
            {
                return false;
            }
        }

        [WebMethod(Description = "Kiem tra dang nhap: (-1: không có username, 0: không đúng pass, 1: đúng)")]
        public int KiemTraDangNhap(string user, string pass)
        {
            var a = (from p in db.TaiKhoans
                     where p.Username == user
                     select p).FirstOrDefault();
            if (a == null)
            {
                return -1;
            }
            else if (a.Password == pass)
            {
                return 1;
            }
            else
            {
                return 0;
            }

        }
    }
}
