package kr.ayukawa.swagger.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.ayukawa.swagger.model.Member;
import kr.ayukawa.swagger.service.IMemberService;

@RestController
@RequestMapping(value="/Members")
public class MemberController {
	@Autowired
	IMemberService memberService;
	
	@ApiOperation(value="멤버 전체 목록 조회", notes="멤버들의 전체 목록을 조회한다.")
	@RequestMapping(method=RequestMethod.GET, produces= {"application/json;charset=utf-8"})
	public ResponseEntity<List<Member>> getAllMembers() {
		return new ResponseEntity<>(memberService.getAllMembers(), HttpStatus.OK);
	}
	
	@ApiOperation(value="멤버 조회", notes="memberId에 해당하는 특정 멤버를 조회한다.")
	@RequestMapping(value="/{memberId}", method=RequestMethod.GET, produces={"application/json;charset=utf-8"})
	public ResponseEntity<Member> getMemberById(@ApiParam(value="멤버 ID", required=true) @PathVariable(value="memberId") String memberId) {
		try {
			int nMemberId = Integer.parseInt(memberId);
			Member member = memberService.getMemberByMemberId(nMemberId);
			
			if(member != null) {
				return new ResponseEntity<>(member, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch(NumberFormatException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value="멤버 추가", notes="새로운 멤버를 추가한다.")
	@ApiImplicitParams(
		@ApiImplicitParam(name="member", value="멤버의 model 형태에 해당하는 JSON 문자열")
	)
	@RequestMapping(method=RequestMethod.POST, produces= {"application/json;charset=utf-8"})
	public ResponseEntity<Member> insertMember(@RequestBody String member) {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			Member o = mapper.readValue(member, Member.class);
			memberService.insertMember(o);
			
			return new ResponseEntity<Member>(o, HttpStatus.CREATED);
		} catch(JsonParseException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch(JsonMappingException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value="멤버 정보 수정", notes="memberId에 해당하는 멤버의 데이터를 수정한다.")
	@ApiImplicitParams(
		@ApiImplicitParam(name="member", value="멤버의 model 형태에 해당하는 JSON 문자열")
	)
	@RequestMapping(method=RequestMethod.PUT, produces={"application/json;charset=utf-8"})
	public ResponseEntity<Member> updateMember(@RequestBody String member) {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			Member o = mapper.readValue(member, Member.class);
			memberService.updateMember(o);
			
			return new ResponseEntity<Member>(o, HttpStatus.OK);
		} catch(JsonParseException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch(JsonMappingException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value="멤버 삭제", notes="memberId에 해당하는 멤버의 데이터를 삭제한다.")
	@ApiImplicitParams(
		@ApiImplicitParam(name="member", value="멤버의 model 형태에 해당하는 JSON 문자열")
	)
	@RequestMapping(value="/{memberId}", method=RequestMethod.DELETE, produces= {"application/json;charset=utf-8"})
	public ResponseEntity<Member> deleteMemberById(@PathVariable(value="memberId") String memberId) {
		try {
			int nMemberId = Integer.parseInt(memberId);
			
			if(memberService.deleteMemberById(nMemberId)) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch(NumberFormatException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
