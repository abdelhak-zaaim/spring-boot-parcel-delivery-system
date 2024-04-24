/*
 * *
 *  * @project : SuiviColis
 *  * @author : Abdelhak Zaaim
 *  * @email : abdelhakzammii@gmail.com
 *  * @created : 23/04/2024, 17:51
 *  * @modified : 23/04/2024, 17:51
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  *
 *  * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *  *
 *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *  *
 */

package com.suivi.colis.suivicolis.models;

import com.suivi.colis.suivicolis.models.enums.Role;
import com.suivi.colis.suivicolis.models.enums.UserStatus;
import com.suivi.colis.suivicolis.utils.Constants;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Entity

@DiscriminatorColumn(name = Constants.USER_ROLE_NAME, discriminatorType = DiscriminatorType.STRING)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;
    private String email;
    private String password;
    @Column(name = Constants.USER_ROLE_NAME, insertable = false, updatable = false)
    private String role;

    private String phone;
    @ManyToOne
    @JoinColumn(name = "idAddress", referencedColumnName = "id")
    private Address idAddress;

    @Temporal(TemporalType.TIMESTAMP)
    private Date registeredAt;

    @Enumerated(EnumType.STRING)
    private UserStatus status;
    @Column(unique = true)
    private String token;
    @Column(unique = true)
    private String refreshToken;

    private String cin;
    private Date dateOfBirth;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }


    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return status != UserStatus.EXPIRED;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status != UserStatus.LOCKED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return status != UserStatus.EXPIRED;
    }

    @Override
    public boolean isEnabled() {
        return status == UserStatus.ACTIVE;
    }


}