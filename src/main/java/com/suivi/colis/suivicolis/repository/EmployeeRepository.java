/*
 * **
 *  * @project : SuiviColis
 *  * @author : Abdelhak Zaaim
 *  * @email : abdelhakzammii@gmail.com
 *  * @created : 23/04/2024, 19:13
 *  * @modified : 23/04/2024, 19:13
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  *
 *  * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *  *
 *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *  **
 */

package com.suivi.colis.suivicolis.repository;

import com.suivi.colis.suivicolis.models.Employee;
import com.suivi.colis.suivicolis.models.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Long> {



}
