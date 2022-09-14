/*
 * Copyright (c) 2022 AtLarge Research
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import {
    Dropdown,
    DropdownToggle,
    Skeleton,
    ToolbarItem,
    DropdownItem,
    DropdownGroup,
    Avatar,
} from '@patternfly/react-core'
import { useReducer } from 'react'
import { useAuth } from '../auth'

export default function AppHeaderUser() {
    const { logout, user, isAuthenticated, isLoading } = useAuth()
    const username = isAuthenticated || isLoading ? user?.name : 'Anonymous'
    const avatar = isAuthenticated || isLoading ? user?.picture : '/img/avatar.svg'

    const [isDropdownOpen, toggleDropdown] = useReducer((t) => !t, false)
    const userDropdownItems = [
        <DropdownGroup key="group 2">
            <DropdownItem
                key="group 2 logout"
                isDisabled={!isAuthenticated}
                onClick={() => logout({ returnTo: window.location.origin })}
            >
                Logout
            </DropdownItem>
        </DropdownGroup>,
    ]

    const avatarComponent = avatar ? (
        <Avatar src={avatar} alt="Avatar image" />
    ) : (
        <Skeleton className="pf-c-avatar" shape="circle" width="2.25rem" screenreaderText="Loading avatar" />
    )

    return (
        <ToolbarItem visibility={{ default: 'hidden', md: 'visible' }}>
            <Dropdown
                isFullHeight
                position="right"
                isOpen={isDropdownOpen}
                toggle={
                    <DropdownToggle onToggle={toggleDropdown} icon={avatarComponent}>
                        {username ?? (
                            <Skeleton
                                fontSize="xs"
                                width="150px"
                                className="pf-u-display-inline-flex"
                                screenreaderText="Loading username"
                            />
                        )}
                    </DropdownToggle>
                }
                dropdownItems={userDropdownItems}
            />
        </ToolbarItem>
    )
}
