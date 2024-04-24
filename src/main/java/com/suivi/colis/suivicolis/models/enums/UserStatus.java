/*
 * **
 *  * @project : SuiviColis
 *  * @author : Abdelhak Zaaim
 *  * @email : abdelhakzammii@gmail.com
 *  * @created : 23/04/2024, 18:18
 *  * @modified : 23/04/2024, 18:16
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  *
 *  * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *  *
 *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *  **
 */

package com.suivi.colis.suivicolis.models.enums;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum UserStatus {
    ACTIVE(new SimpleGrantedAuthority("ACTIVE")),
    INACTIVE(new SimpleGrantedAuthority("INACTIVE")),
    SUSPENDED(new SimpleGrantedAuthority("SUSPENDED")),
    DELETED(new SimpleGrantedAuthority("DELETED")),
    EXPIRED(new SimpleGrantedAuthority("EXPIRED")),

    LOCKED(new SimpleGrantedAuthority("LOCKED"));


    private final GrantedAuthority grantedAuthority;

    UserStatus(GrantedAuthority grantedAuthority) {
        this.grantedAuthority = grantedAuthority;
    }

    public GrantedAuthority getGrantedAuthority() {
        return grantedAuthority;
    }
}