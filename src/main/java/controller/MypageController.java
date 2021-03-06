package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.MypageDao;
import model.BookmemberDataBean;
import model.InterestDataBean;
import model.LogonDataBean;
import model.MyPageDataBean;
import model.NoteDataBean;
import model.RoomBookDataBean;
import model.RoomDataBean;
import service.MybatisInterestDaoOracle;
import service.MybatisLogonDaoOracle;
import service.MybatisMypageDaoOracle;
import service.MybatisNoteDaoOracle;
import service.RoomDaoMybatis;


@Controller
@RequestMapping("/mypage/")
public class MypageController{

	private String email;
	private String name;
	
	@Autowired
	MybatisMypageDaoOracle dbPro;
	
	@Autowired
	RoomDaoMybatis Roomservice;
	
	@Autowired
	MybatisLogonDaoOracle Logonservice;
	
	@Autowired
	MybatisInterestDaoOracle Interservice;
	
	@Autowired
	MybatisNoteDaoOracle noteservice;
	
	@ModelAttribute
	public void initProcess(HttpServletRequest request) {
		HttpSession session = request.getSession();
			
			this.email = (String) session.getAttribute("memEmail");
			this.name = (String) session.getAttribute("name");
		
		
	}
	
	@RequestMapping(value="mypage2", method = RequestMethod.GET)
	public String Room_myPages(@ModelAttribute("member") LogonDataBean member, Model model) throws Exception {
		System.out.println(member + "--> member");
		System.out.println(name + "--> name");
		System.out.println(email + "--> email");
		member.setName(name);
		int memberLikecheck = dbPro.getMemberLikeCheck(name);
		System.out.println(memberLikecheck + " ---> ??????????????? memberLike????????????");
		LogonDataBean setmember = Logonservice.getUser(email);
		System.out.println(setmember + "--> setmember");
		List<InterestDataBean> setMemInter = dbPro.getMemLike(name);
		System.out.println(setMemInter + "--> setMemInter");
		
		String address = setmember.getAddress(); // ??????
		String[] arr = address.split(" ");
		String setAddress = arr[0];
		System.out.println(setmember.getBirthday());
		
		model.addAttribute("setAddress", setAddress);
		model.addAttribute("setMemInter", setMemInter);
		model.addAttribute("setmember", setmember);
		model.addAttribute("memberLikecheck", memberLikecheck);
		
		return "content/mypage/mypage2";
	}
	
	@RequestMapping(value = "myRoomList", method = RequestMethod.GET)
	public String Room_myRoomList(Model model) throws ServletException, IOException {
		
		List<MyPageDataBean> myinfo = dbPro.getMyRoomList(email);
		System.out.println(myinfo + "?????? ????????? ??????");
		model.addAttribute("myinfo", myinfo);
		return "content/mypage/myRoomList";
	}
	
	@RequestMapping(value = "mypage", method = RequestMethod.GET, produces="application/text; charset=utf8")
	public String Room_myPage(HttpServletRequest request,Model m) throws ServletException, IOException{
		HttpSession session = request.getSession();
		MypageDao service = MypageDao.getInstance();
		String email = (String) session.getAttribute("memEmail");
		System.out.println(email);
		
		List<MyPageDataBean> li = dbPro.getMyRoomList(email);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@"+li.size());
		List<Integer> nowcnt = service.getCount(email);
		List<RoomDataBean> roomli = new ArrayList<RoomDataBean>();
		
//		for(int i=0; i<li.size(); i++) {
//			System.out.println( li.get(i).getClassnum() +" : roomli.get(i).getNum() ");
//			roomli.add(dbPro.getHobbyclassNum(li.get(i).getClassnum())); //?????? ????????? roomli??? ??????
//		
//		}
//		List<RoomBookDataBean> roombookli = new ArrayList<RoomBookDataBean>();
		List<List> rooaa = new ArrayList<List>();
		System.out.println(name+"@@@@name ??????");
		List<BookmemberDataBean> bookli = dbPro.getbookmem(name);
		
		
		
		for(int i=0; i<li.size(); i++) {
			System.out.println( li.get(i).getClassnum() +" : roomli.get(i).getNum() ");
			rooaa.add(dbPro.getClassBook(li.get(i).getClassnum() )) ;
		}
		m.addAttribute("nowcnt", nowcnt);
		m.addAttribute("roaa",rooaa);
		m.addAttribute("bookli",bookli);
		System.out.println(bookli+"@@@@@ bookilid");
		
		return "content/mypage/mypage";
	}
	
	@RequestMapping(value = "mypage", method = RequestMethod.POST, produces="application/text; charset=utf8")
	public String Room_myPagepost(HttpServletRequest request,Model m) throws ServletException, IOException{
		HttpSession session = request.getSession();
		MypageDao service = MypageDao.getInstance();
		String email = (String) session.getAttribute("memEmail");
		System.out.println(email);
		
		List<MyPageDataBean> li = dbPro.getMyRoomList(email);
		List<Integer> nowcnt = service.getCount(email);
		List<RoomDataBean> roomli = new ArrayList<RoomDataBean>();
		
		for(int i=0; i<li.size(); i++) {
			roomli.add(dbPro.getHobbyclassNum(li.get(i).getClassnum())); //?????? ????????? roomli??? ??????
		
		}
		List<RoomBookDataBean> roombookli = new ArrayList<RoomBookDataBean>();
		List<List> rooaa = new ArrayList<List>();
		List<BookmemberDataBean> bookli = dbPro.getbookmem(name);
		
		for(int i=0; i<roomli.size(); i++) {
			System.out.println(roomli.get(i).getNum());
			rooaa.add(dbPro.getClassBook( roomli.get(i).getNum() )) ;
		}
		System.out.println("@@@@@@@@@roomlis"+rooaa.size());
		m.addAttribute("nowcnt", nowcnt);
		m.addAttribute("roombookli", roombookli);
		m.addAttribute("roaa",rooaa);
		m.addAttribute("bookli",bookli);
		System.out.println(bookli);
		
		return "content/mypage/mypage";
	}
	
	// ?????? ????????? ?????????
	@RequestMapping(value="popupnoteWrite", method = RequestMethod.GET)
	public String Mypage_popupnoteWrite(@RequestParam("memid") int noteRecieverMemid, Model model) throws Exception {
		System.out.println("??????????????? ????????? ??????");
		System.out.println("### " + noteRecieverMemid);
		model.addAttribute("noteRecieverMemid", noteRecieverMemid);
		model.addAttribute("name", name);
		return "pop/popupnoteWrite";
	}
	
	// ?????? ????????? _ ????????? ??????
	@ResponseBody
	@RequestMapping(value="popupnoteWrite", method=RequestMethod.POST)
	public String popupnoteWrite(@RequestParam("notereceiver") int notereceiver, NoteDataBean note) throws Exception {
		System.out.println("????????????????????????????????? ????????????????????????????????????");
		System.out.println("???????????? memid : " + notereceiver);
		note.setNotesender(name); // ????????? ?????? (???????????? ???) ????????????
		String notereceiver2 = noteservice.getNoteReceiverName(notereceiver);
		note.setNotereceiver(notereceiver2); // ?????? ?????? ????????????
		note.setStatus(0);
		noteservice.insertNote(note);
		
		return "OK";
	}
	// @RequestParam("noteid") String noteid 
	// ?????? ?????? ????????? _ ????????? ??????
		@ResponseBody
		@RequestMapping(value="popupnoteWrite2", method=RequestMethod.POST)
		public String popupnoteWrite2(@RequestParam("notereceiver") String notereceiver, NoteDataBean note) throws Exception {
			System.out.println("????????????????????????????????? ????????????????????????????????????");
			System.out.println("?????? ~>  : " + note);
			note.setNotesender(name); // ????????? ?????? (???????????? ???) ????????????
			note.setStatus(0); // ?????? ???????????? 0
			note.setRethatid(note.getNoteid()); // ????????? ?????? ??? id ??????
			
			// ?????? ??? noteid ????????????
			noteservice.insertNote2(note);
		
			return "OK";
		}
	
	// ??????????????? _ ????????????
	@RequestMapping(value="popupnote", method = RequestMethod.GET)
	public String Mypage_popupnote(Model model) throws Exception {
		System.out.println("########### ??????????????? ???????????? ????????? ?????? ###########");
		List<NoteDataBean> myNoteSendList = noteservice.getMyNoteSendList(name); // ?????? ?????? ?????? ????????????
		List<NoteDataBean> myNoteReceiveList = noteservice.getMyNoteReceiveList(name);
		System.out.println("?????? ?????? ????????? : " + myNoteSendList);
		System.out.println("?????? ?????? ????????? : " + myNoteReceiveList);
		model.addAttribute("myNoteSendList", myNoteSendList);
		model.addAttribute("myNoteReceiveList", myNoteReceiveList);
		
		return "pop/popupnote";
	}
	
	// ?????? ?????? ?????? -> ??????
	@RequestMapping(value="popupnoteRead", method=RequestMethod.POST)
	public @ResponseBody String popupnoteRead(@RequestParam("noteid") String noteid) throws Exception {
		System.out.println("????????????????????????????????? ?????? ?????? -> ???????????? ???????????????????????????");
		System.out.println(noteid);
		noteservice.updateMyNoteStatus(noteid);
		return "OK";
	}
	

}
