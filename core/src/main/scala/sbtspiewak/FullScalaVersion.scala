/*
 * Copyright 2018 Daniel Spiewak
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sbtspiewak

object FullScalaVersion {
  private val Release = """^(\d+)\.(\d+)\.(\d+)$""".r
  private val Milestone = """^(\d+)\.(\d+)\.(\d+)-M(\d+)$""".r
  private val Snapshot = """^(\d+)\.(\d+)\.(\d+)-M(\d+)(.+)$""".r

  /**
   * Returns major, minor, build, milestone (optional), qualifier (optional)
   */
  def unapply(version: String): Option[(Int, Int, Int, Option[Int], Option[String])] = version match {
    case Release(major, minor, build) =>
      Some((major.toInt, minor.toInt, build.toInt, None, None))

    case Milestone(major, minor, build, milestone) =>
      Some((major.toInt, minor.toInt, build.toInt, Some(milestone.toInt), None))

    case Snapshot(major, minor, build, milestone, qualifier) =>
      Some((major.toInt, minor.toInt, build.toInt, Some(milestone.toInt), Some(qualifier)))
  }
}
