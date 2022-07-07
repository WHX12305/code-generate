package ${bashPackage}.controller;

<#if enableSwagger==true>import io.swagger.annotations.Api;</#if>
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * ${table.name}Controller
 * </p>
 *
 * @author ${author}
 * @since ${dateTime}
 */
@RestController
@RequestMapping("${table.name}")
<#if enableSwagger==true>@Api(tags = {"${table.uName}Controller"})</#if>
public class MessageController {

}