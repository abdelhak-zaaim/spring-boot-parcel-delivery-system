/*
 * **
 *  * @project : SuiviColis
 *  * @author : Abdelhak Zaaim
 *  * @email : abdelhakzammii@gmail.com
 *  * @created : 23/04/2024, 17:52
 *  * @modified : 23/04/2024, 17:52
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  *
 *  * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *  *
 *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *  **
 */

package com.suivi.colis.suivicolis.models;

import com.suivi.colis.suivicolis.models.enums.ParcelType;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;

    private String codeBar;
    
    private float height;
    private float width;
    
    private float whight;

    @Enumerated(EnumType.STRING)
    private ParcelType Type;

    private Date creationDate;
    private Date estimatedDeliveryDate;
    private Date deleveryDate;

    @ManyToOne
    @JoinColumn(name = "departedUser", referencedColumnName = "id")
    private User departedUser;

    @ManyToOne
    @JoinColumn(name = "destinationUser", referencedColumnName = "id")
    private User destinationUser;

    @ManyToOne
    private AgencyOwner departedAgency;
    @ManyToOne
    private AgencyOwner destinationAgency;

    
}
