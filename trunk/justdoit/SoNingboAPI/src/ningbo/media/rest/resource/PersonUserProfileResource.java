package ningbo.media.rest.resource;

import javax.annotation.Resource;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ningbo.media.bean.PersonUserProfile;
import ningbo.media.rest.util.Constant;
import ningbo.media.rest.util.JSONCode;
import ningbo.media.rest.util.StringUtils;
import ningbo.media.service.PersonUserProfileService;

import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/userprofile")
@Component
@Scope("request")
public class PersonUserProfileResource {

	@Resource
	private PersonUserProfileService personUserProfileService;

	@Path("/addOrUpdate")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addOrUpdateUserProfile(@FormParam("constellation")
	String constellation, @FormParam("birthday")
	String birthday, @FormParam("stature")
	String stature, @FormParam("blood")
	String blood, @FormParam("qq")
	String qq, @FormParam("msn")
	String msn, @FormParam("age")
	String age, @FormParam("phone")
	String phone, @FormParam("cellPhone")
	String cellPhone, @FormParam("homeArea")
	String homeArea, @FormParam("likeGames")
	String likeGames, @FormParam("likeMovies")
	String likeMovies, @FormParam("likeBooks")
	String likeBooks, @FormParam("likeMusic")
	String likeMusic, @FormParam("key")
	String key, @FormParam("md5Value")
	String md5Value, @FormParam("profileId")
	String profileId) {
		JSONObject json = new JSONObject();
		PersonUserProfile profile = null;
		try {
			if (!StringUtils.hasText(key)) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_KEY_ISNULL);
				return Response.ok(json.toString()).build();
			} else if (!Constant.KEY.equals(key)) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_KEY_INVALID);
				return Response.ok(json.toString()).build();
			}

			if (null == profileId || "".equals(profileId)) {
				profile = new PersonUserProfile();

				profile.setBirthday(birthday);
				profile.setBlood(blood);
				profile.setAge(Integer.valueOf(age));
				profile.setCellPhone(cellPhone);
				profile.setConstellation(constellation);
				profile.setHomeArea(homeArea);
				profile.setLikeBooks(likeBooks);
				profile.setLikeGames(likeGames);
				profile.setLikeMusic(likeMusic);
				profile.setLikeMovies(likeMovies);
				profile.setMsn(msn);
				profile.setQq(qq);
				profile.setPhone(phone);
				profile.setStature(Integer.valueOf(stature));

			} else {
				profile = personUserProfileService.get(Integer
						.valueOf(profileId));
			}

		} catch (Exception ex) {

		}

		return null;
	}
}
