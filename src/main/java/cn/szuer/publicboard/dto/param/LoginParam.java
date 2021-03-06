package cn.szuer.publicboard.dto.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginParam
{
    /**
     * 账号
     * @mock 2019xxxxxx
     */
    private Integer userid;
    /**
     * 密码
     * @mock 123456789
     */
    private String password;

}

